package com.fmatheus.app.controller.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;

public class CustomAuthenticationConverter implements AuthenticationConverter {

    @Nullable
    @Override
    public Authentication convert(HttpServletRequest request) {

        MultiValueMap<String, String> parameters = getParameters(request);

        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);

        Set<String> requestedScopes = CustomOAuth2ParameterNames.CUSTOM_GRANT_TYPE.equals(grantType) ? this.customCredentials(parameters, grantType) :
                this.clientCredentials(parameters);

        Map<String, Object> additionalParameters = this.additionalParameters(parameters);

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        return new CustomAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);

    }


    private Set<String> customCredentials(MultiValueMap<String, String> parameters, String grantType) {

        if (!CustomOAuth2ParameterNames.CUSTOM_GRANT_TYPE.equals(grantType)) {
            throw new OAuth2AuthenticationException(OAuthUtil.grantTypeInvalidError());
        }

        if (!StringUtils.hasText(grantType) || parameters.get(OAuth2ParameterNames.GRANT_TYPE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuthUtil.grantTypeError());
        }

        var username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.hasText(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
            throw new OAuth2AuthenticationException(OAuthUtil.usernameError());
        }

        var password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.hasText(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
            throw new OAuth2AuthenticationException(OAuthUtil.passwordError());
        }

        var uuidSystem = parameters.getFirst(CustomOAuth2ParameterNames.UUID_SYSTEM);
        if (!StringUtils.hasText(uuidSystem) || parameters.get(CustomOAuth2ParameterNames.UUID_SYSTEM).size() != 1) {
            throw new OAuth2AuthenticationException(OAuthUtil.systemError());
        }

        var zipCode = parameters.getFirst(CustomOAuth2ParameterNames.ZIP_CODE);
        if (!StringUtils.hasText(zipCode) || parameters.get(CustomOAuth2ParameterNames.ZIP_CODE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuthUtil.zipCodeError());
        }

        var scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        return requestedScopes;
    }

    private Set<String> clientCredentials(MultiValueMap<String, String> parameters) {

        var scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        return requestedScopes;
    }

    /**
     * Adiciona parametros adicionais para serem iseridos na {@link CustomAuthenticationToken}
     *
     * @param parameters MultiValueMap
     * @return Map<String, Object>
     */
    private Map<String, Object> additionalParameters(MultiValueMap<String, String> parameters) {
        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) && !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, value.get(0));
            }
        });
        return additionalParameters;
    }

    private static MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach((key, values) -> {
            for (String value : values) {
                parameters.add(key, value);
            }
        });
        return parameters;
    }


}
