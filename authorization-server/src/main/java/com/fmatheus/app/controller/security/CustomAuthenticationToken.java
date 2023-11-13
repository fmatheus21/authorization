package com.fmatheus.app.controller.security;


import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import java.io.Serial;
import java.util.*;

@Getter
public class CustomAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {


    @Serial
    private static final long serialVersionUID = 1L;
    private final String username;
    private final String password;
    private final UUID uuidSystem;
    private final String ipAddress;
    private final String city;
    private final String country;
    private final String latitude;
    private final String longitude;
    private final String state;
    private final Set<String> scopes;

    public CustomAuthenticationToken(Authentication clientPrincipal, @Nullable Set<String> scopes, @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType("custom_password"), clientPrincipal, additionalParameters);
        this.username = (String) Objects.requireNonNull(additionalParameters).get("username");
        this.password = (String) Objects.requireNonNull(additionalParameters).get("password");
        this.uuidSystem = UUID.fromString(Objects.requireNonNull(additionalParameters).get("uuid_system").toString());
        this.ipAddress = (String) Objects.requireNonNull(additionalParameters).get("ip_address");
        this.city = (String) Objects.requireNonNull(additionalParameters).get("city");
        this.country = (String) Objects.requireNonNull(additionalParameters).get("country");
        this.latitude = (String) Objects.requireNonNull(additionalParameters).get("latitude");
        this.longitude = (String) Objects.requireNonNull(additionalParameters).get("longitude");
        this.state = (String) Objects.requireNonNull(additionalParameters).get("state");
        this.scopes = Collections.unmodifiableSet(scopes != null ? new HashSet<>(scopes) : Collections.emptySet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomAuthenticationToken that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUsername());
    }

}
