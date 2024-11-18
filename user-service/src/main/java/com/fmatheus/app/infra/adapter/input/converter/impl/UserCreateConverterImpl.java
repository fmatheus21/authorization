package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.application.domain.*;
import com.fmatheus.app.infra.adapter.config.properties.CryptoProperties;
import com.fmatheus.app.infra.adapter.input.converter.UserCreateConverter;
import com.fmatheus.app.infra.adapter.input.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.infra.adapter.input.util.PasswordGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserCreateConverterImpl implements UserCreateConverter {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CryptoProperties cryptoProperties;

    @Override
    public PersonDomain converterToEntity(UserCreateDtoRequest request) {
        var date = LocalDateTime.now();
        var person = this.mapper.map(request, PersonDomain.class);
        person.setCreatedAt(date);
        person.setId(null);

        var personType = person.getPersonType();
        personType.setId(request.getPersonType().getId());
        person.setPersonType(personType);

        var address = person.getAddress();
        address.setPerson(person);

        var contact = person.getContact();
        contact.setPerson(person);

        var permissions = new ArrayList<>(request.getPermissions().stream().map(this::converterPermission).toList());

        var profile = new ProfileDomain();
        profile.setId(request.getProfile().getId());
        profile.setName(request.getProfile().getName());

        var user = new UserDomain();
        user.setUuid(UUID.randomUUID());
        user.setPerson(person);
        user.setProfile(profile);
        user.setActive(true);
        user.setUsername(person.getDocument());
        user.setPassword(this.passwordEncoder.encode(PasswordGeneratorUtil.randomPassword(this.cryptoProperties.getRandomPassword())));
        user.setCreatedAt(date);
        user.setUpdatedAt(date);
        user.setPermissions(permissions);

        person.setAddress(address);
        person.setContact(contact);
        person.setUsers(Collections.singleton(user));

        return person;
    }

    @Override
    public UserDtoResponse converterToResponse(PersonDomain personDomain) {
        return this.mapper.map(personDomain, UserDtoResponse.class);
    }

    private PermissionDomain converterPermission(UserCreateDtoRequest.PermissionRequest request) {
        var permission = new PermissionDomain();
        permission.setName(request.getName());
        permission.setId(request.getId());
        return permission;
    }

}
