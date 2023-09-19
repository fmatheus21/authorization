package com.fmatheus.app.controller.converter.impl;

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

@RequiredArgsConstructor
@Component
public class UserCreateConverterImpl implements UserCreateConverter {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Person converterToEntity(UserCreateRequest request) {
        // var person = this.mapper.map(request, Person.class);
        var personType = PersonType.builder().build();
        personType.setId(request.getPerson().getPersonTypeUuid());
        var person = Person.builder()
                .name(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getName()))
                .document(CharacterUtil.removeSpecialCharacters(request.getPerson().getDocument()))
                .personType(personType)

                .build();
        var user = User.builder()
                .person(person)
                .active(true)
                .username(CharacterUtil.removeSpecialCharacters(request.getPerson().getDocument()))
                .password(passwordEncoder.encode(PasswordGeneratorUtil.randomPassword(8)))
                .build();

        var address = Address.builder()
                .person(person)
                .place(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getPlace()))
                .number(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getNumber()))
                .complement(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getComplement()))
                .district(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getDistrict()))
                .city(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getCity()))
                .state(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getAddress().getState()))
                .zipCode(CharacterUtil.removeSpecialCharacters(request.getPerson().getAddress().getZipCode()))
                .build();

        var contact = Contact.builder()
                .person(person)
                .email(CharacterUtil.convertAllUppercaseCharacters(request.getPerson().getContact().getEmail()))
                .phone(CharacterUtil.removeSpecialCharacters(request.getPerson().getContact().getPhone()))
                .build();

        person.setAddress(address);
        person.setContact(contact);
        person.setUser(user);

        return person;
    }

    @Override
    public UserCreateResponse converterToResponse(Person person) {
        return null;
    }


}
