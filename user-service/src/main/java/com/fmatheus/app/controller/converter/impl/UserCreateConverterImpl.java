package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.config.properties.CryptoProperties;
import com.fmatheus.app.controller.converter.UserCreateConverter;
import com.fmatheus.app.controller.dto.request.create.UserCreateRequest;
import com.fmatheus.app.controller.dto.response.create.UserCreateResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.controller.util.PasswordGeneratorUtil;
import com.fmatheus.app.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserCreateConverterImpl implements UserCreateConverter {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final CryptoProperties cryptoProperties;

    @Override
    public Person converterToEntity(UserCreateRequest request) {

        var person = this.mapper.map(request, Person.class);

        var personType = PersonType.builder()
                .uuid(UUID.randomUUID())
                .build();
        personType.setId(request.getPersonTypeId());

        person.setName(CharacterUtil.convertAllUppercaseCharacters(person.getName()));
        person.setDocument(CharacterUtil.removeSpecialCharacters(person.getDocument()));
        person.setPersonType(personType);

        var user = User.builder()
                .uuid(UUID.randomUUID())
                .person(person)
                .active(true)
                .username(CharacterUtil.removeSpecialCharacters(person.getDocument()))
                .password(this.passwordEncoder.encode(PasswordGeneratorUtil.randomPassword(this.cryptoProperties.getRandomPassword())))
                .build();

        var address = Address.builder()
                .person(person)
                .place(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getPlace()))
                .number(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getNumber()))
                .complement(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getComplement()))
                .district(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getDistrict()))
                .city(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getCity()))
                .state(CharacterUtil.convertAllUppercaseCharacters(person.getAddress().getState()))
                .zipCode(CharacterUtil.removeSpecialCharacters(person.getAddress().getZipCode()))
                .build();

        var contact = Contact.builder()
                .person(person)
                .email(CharacterUtil.convertAllUppercaseCharacters(person.getContact().getEmail()))
                .phone(CharacterUtil.removeSpecialCharacters(person.getContact().getPhone()))
                .build();

        person.setAddress(address);
        person.setContact(contact);
        person.setUser(user);

        return person;
    }

    @Override
    public UserCreateResponse converterToResponse(Person person) {
        throw new UnsupportedOperationException();
    }


}
