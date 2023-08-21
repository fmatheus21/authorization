package com.fmatheus.app.controller.security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Stream;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
        return new HashSet<>(jwt.getClaimAsStringList("authorities")
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList());
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(this.jwtGrantedAuthoritiesConverter.convert(source).stream(), extractResourceRoles(source).stream()).toList();
        return new JwtAuthenticationToken(source, authorities);
    }

}