package com.fmatheus.app.controller.security;


import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.UserSessions;
import com.fmatheus.app.model.service.UserSessionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.*;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String GRANT_TYPE = "custom_password";
    private static final String ERROR_URI = "https://datatracker.ietf.org/doc/html/rfc6749#section-5.2";
    private String username = "";
    private String password = "";
    private UUID uuidSystem;
    private String ipAddress;
    private String city;
    private String country;
    private String latitude;
    private String longitude;
    private String state;
    private final OAuth2AuthorizationService authorizationService;
    private final UserDetailsService userDetailsService;
    private OAuth2Authorization.Builder authorizationBuilder;
    private DefaultOAuth2TokenContext.Builder tokenContextBuilder;
    private OAuth2TokenContext tokenContext;
    private OAuth2AccessToken accessToken;
    private RegisteredClient registeredClient;
    private OAuth2ClientAuthenticationToken clientPrincipal;
    private CustomAuthenticationToken customAuthenticationToken;
    private OAuth2RefreshToken refreshToken;
    private final OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator;
    private final Set<String> authorizedScopes = new HashSet<>();
    private CustomUserDetails customUserDetails;

    @Autowired
    private UserSessionsService userSessionsService;


    public CustomAuthenticationProvider(OAuth2AuthorizationService authorizationService, OAuth2TokenGenerator<? extends OAuth2Token> tokenGenerator, UserDetailsService userDetailsService) {
        Assert.notNull(authorizationService, "authorizationService nao pode ser nulo");
        Assert.notNull(tokenGenerator, "TokenGenerator nao pode ser nulo");
        Assert.notNull(userDetailsService, "UserDetailsService nao pode ser nulo");
        this.authorizationService = authorizationService;
        this.tokenGenerator = tokenGenerator;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        this.customAuthenticationToken = (CustomAuthenticationToken) authentication;
        this.clientPrincipal = getAuthenticatedClientElseThrowInvalidClient(this.customAuthenticationToken);
        this.registeredClient = this.clientPrincipal.getRegisteredClient();
        this.username = CharacterUtil.convertAllLowercaseCharacters(this.customAuthenticationToken.getUsername());
        this.password = this.customAuthenticationToken.getPassword();
        this.uuidSystem = this.customAuthenticationToken.getUuidSystem();
        this.ipAddress = this.customAuthenticationToken.getIpAddress();
        this.city = this.customAuthenticationToken.getCity();
        this.country = this.customAuthenticationToken.getCountry();
        this.latitude = this.customAuthenticationToken.getLatitude();
        this.longitude = this.customAuthenticationToken.getLongitude();
        this.state = this.customAuthenticationToken.getState();
        User user = this.validateUser();
        this.authorizedScopes.addAll(registeredClient.getScopes());
        this.generateNewContextHolder(user);
        this.defaultOAuth2TokenContext();
        this.authorizationBuilder();
        this.generateAccessToken();
        this.generateRefreshToken();
        OAuth2Authorization authorization = this.authorizationBuilder.build();
        this.authorizationService.save(authorization);
        return new OAuth2AccessTokenAuthenticationToken(this.registeredClient, this.clientPrincipal, this.accessToken, this.refreshToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private static OAuth2ClientAuthenticationToken getAuthenticatedClientElseThrowInvalidClient(Authentication authentication) {
        OAuth2ClientAuthenticationToken clientPrincipal = null;
        if (OAuth2ClientAuthenticationToken.class.isAssignableFrom(authentication.getPrincipal().getClass())) {
            clientPrincipal = (OAuth2ClientAuthenticationToken) authentication.getPrincipal();
        }

        if (clientPrincipal != null && clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }

        throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
    }


    private User validateUser() {

        try {
            this.customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(this.username);
        } catch (UsernameNotFoundException e) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.ACCESS_DENIED);
        }

        log.info("Comparando senha do usuario: {}", username);
        CharSequence charPassword = new StringBuilder(this.password);
        if (!this.passwordEncoder.matches(charPassword, this.customUserDetails.getPassword()) || !Objects.requireNonNull(CharacterUtil.convertAllLowercaseCharacters(this.customUserDetails.getUsername())).equals(this.username)) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.ACCESS_DENIED);
        }
        log.info("Senha do usuario {} confirmada.", username);

        log.info("Verificando se o usuario {} tem permissao para acessar o sistema.", username);
        var systems = this.customUserDetails.getUser().getPermissions().stream().filter(filter -> filter.getSystem().getUuid().equals(this.uuidSystem)).toList();
        if (systems.isEmpty()) {
            log.error("O usuario {} foi autenticado, mas nao tem permissao para entrar neste sistema.", username);
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.ACCESS_DENIED);
        }

        log.info("O usuario {} tem permissao para acessar o sistema.", username);

        return this.customUserDetails;
    }

    private void defaultOAuth2TokenContext() {
        this.tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(this.registeredClient)
                .principal(this.clientPrincipal)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .authorizedScopes(this.authorizedScopes)
                .authorizationGrantType(new AuthorizationGrantType(GRANT_TYPE))
                .authorizationGrant(this.customAuthenticationToken);
    }

    private void authorizationBuilder() {
        this.authorizationBuilder = OAuth2Authorization.withRegisteredClient(this.registeredClient)
                .attribute(Principal.class.getName(), this.clientPrincipal)
                .principalName(this.clientPrincipal.getName())
                .authorizationGrantType(new AuthorizationGrantType(GRANT_TYPE))
                .authorizedScopes(this.authorizedScopes);
    }


    /**
     * Cria um novo contexto de seguranca.
     *
     * @param user User
     */
    private void generateNewContextHolder(User user) {
        log.info("Gerando novo Context Holder.");
        var oAuth2ClientAuthenticationToken = (OAuth2ClientAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        var customUser = new CustomUser(this.customUserDetails, user.getAuthorities());
        oAuth2ClientAuthenticationToken.setDetails(customUser);

        var context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(oAuth2ClientAuthenticationToken);
        SecurityContextHolder.setContext(context);
        log.info("Context Holder gerado.");
    }


    /**
     * Gerar access token.
     */
    private void generateAccessToken() {
        log.info("Gerando Access Token.");
        this.tokenContext = this.tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build();

        var generatedAccessToken = this.tokenGenerator.generate(tokenContext);
        if (generatedAccessToken == null) {
            throw new OAuth2AuthenticationException(this.oAuth2Error());
        }

        this.accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(), generatedAccessToken.getExpiresAt(), this.tokenContext.getAuthorizedScopes());
        if (generatedAccessToken instanceof ClaimAccessor) {
            this.authorizationBuilder.token(this.accessToken, metadata -> metadata.put(OAuth2Authorization.Token.CLAIMS_METADATA_NAME, ((ClaimAccessor) generatedAccessToken).getClaims()));
        } else {
            this.authorizationBuilder.accessToken(this.accessToken);
        }
        log.info("Access Token gerado.");
    }

    /**
     * Gerar refresh token.
     */
    private void generateRefreshToken() {
        log.info("Gerando Refresh Token.");
        if (Objects.requireNonNull(this.registeredClient).getAuthorizationGrantTypes().contains(AuthorizationGrantType.REFRESH_TOKEN) &&
                !this.clientPrincipal.getClientAuthenticationMethod().equals(ClientAuthenticationMethod.NONE)) {

            this.tokenContext = this.tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build();
            var generatedRefreshToken = this.tokenGenerator.generate(this.tokenContext);
            if (!(generatedRefreshToken instanceof OAuth2RefreshToken)) {
                throw new OAuth2AuthenticationException(this.oAuth2Error());
            }
            this.refreshToken = (OAuth2RefreshToken) generatedRefreshToken;
            this.authorizationBuilder.refreshToken(this.refreshToken);
            log.info("Refresh Token gerado.");

            var systems = this.customUserDetails.getUser().getPermissions().stream().filter(filter -> filter.getSystem().getUuid().equals(this.uuidSystem)).toList();

            log.info("Salvando informacoes de login.");
            var userSessions = UserSessions.builder()
                    .ipAddress(this.ipAddress)
                    .city(CharacterUtil.convertAllUppercaseCharacters(this.city))
                    .country(CharacterUtil.convertAllUppercaseCharacters(this.country))
                    .latitude(this.latitude)
                    .longitude(this.longitude)
                    .state(CharacterUtil.convertAllUppercaseCharacters(this.state))
                    .date(LocalDateTime.now())
                    .user(this.customUserDetails.getUser())
                    .system(systems.get(0).getSystem())
                    .build();

            this.userSessionsService.save(userSessions);

            log.info("Informacoes de login salvas.");
        }
    }

    private OAuth2Error oAuth2Error() {
        return new OAuth2Error(OAuth2ErrorCodes.SERVER_ERROR, "O gerador de token falhou ao gerar o token de acesso.", ERROR_URI);
    }

}

