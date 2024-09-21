package com.fmatheus.app.infra.adapter.input.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Stream;

@Slf4j
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {

        if (jwt.getClaims().get("sub").toString().equalsIgnoreCase(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue())) {
            return new HashSet<>(Collections.singleton(new SimpleGrantedAuthority(AuthorizationGrantType.CLIENT_CREDENTIALS.getValue())));
        }

        log.info("Extraindo funcoes de recursos: {}", jwt.getClaimAsStringList("authorities"));
        return new HashSet<>(jwt.getClaimAsStringList("authorities")
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        log.info("Converter token.");
        Collection<GrantedAuthority> authorities = Stream.concat(this.jwtGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream()).toList();
        return new JwtAuthenticationToken(source, authorities);
    }

}