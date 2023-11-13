package com.fmatheus.app.controller.security;

import com.fmatheus.app.controller.CustomOAuth2ParameterNames;
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

        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);

        if (!"custom_password".equals(grantType)) {
            return null;
        }

        MultiValueMap<String, String> parameters = getParameters(request);

        var scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) && parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.hasText(username) || parameters.get(OAuth2ParameterNames.USERNAME).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.hasText(password) || parameters.get(OAuth2ParameterNames.PASSWORD).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var uuidSystem = parameters.getFirst(CustomOAuth2ParameterNames.UUID_SYSTEM);
        if (!StringUtils.hasText(uuidSystem) || parameters.get(CustomOAuth2ParameterNames.UUID_SYSTEM).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var city = parameters.getFirst(CustomOAuth2ParameterNames.CITY);
        if (!StringUtils.hasText(city) || parameters.get(CustomOAuth2ParameterNames.UUID_SYSTEM).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var country = parameters.getFirst(CustomOAuth2ParameterNames.COUNTRY);
        if (!StringUtils.hasText(country) || parameters.get(CustomOAuth2ParameterNames.COUNTRY).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var state = parameters.getFirst(CustomOAuth2ParameterNames.STATE);
        if (!StringUtils.hasText(state) || parameters.get(CustomOAuth2ParameterNames.STATE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var latitude = parameters.getFirst(CustomOAuth2ParameterNames.LATITUDE);
        if (!StringUtils.hasText(latitude) || parameters.get(CustomOAuth2ParameterNames.LATITUDE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var longitude = parameters.getFirst(CustomOAuth2ParameterNames.LONGITUDE);
        if (!StringUtils.hasText(longitude) || parameters.get(CustomOAuth2ParameterNames.LONGITUDE).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        var ipAddress = parameters.getFirst(CustomOAuth2ParameterNames.IP_ADDRESS);
        if (!StringUtils.hasText(ipAddress) || parameters.get(CustomOAuth2ParameterNames.IP_ADDRESS).size() != 1) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_REQUEST);
        }

        Set<String> requestedScopes = null;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, " ")));
        }

        Map<String, Object> additionalParameters = new HashMap<>();
        parameters.forEach((key, value) -> {
            if (!key.equals(OAuth2ParameterNames.GRANT_TYPE) && !key.equals(OAuth2ParameterNames.SCOPE)) {
                additionalParameters.put(key, value.get(0));
            }
        });


        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();
        return new CustomAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
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
