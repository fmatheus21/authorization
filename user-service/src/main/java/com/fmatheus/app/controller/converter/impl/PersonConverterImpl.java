package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Permission;
import com.fmatheus.app.model.entity.Person;
import com.fmatheus.app.model.entity.Systems;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;


@RequiredArgsConstructor
@Component
public class PersonConverterImpl implements PersonConverter {

    private final ModelMapper mapper;

    @Override
    public Person converterToEntity(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonResponse converterToResponse(Person person) {
        this.converterPerson(person);
        return this.mapper.map(person, PersonResponse.class);
    }

    private void converterPerson(Person person) {
        person.setName(CharacterUtil.convertFirstUppercaseCharacter(person.getName()));

        var personType = person.getPersonType();
        personType.setName(CharacterUtil.convertFirstUppercaseCharacter(personType.getName()));

        var address = person.getAddress();
        address.setPlace(CharacterUtil.convertFirstUppercaseCharacter(address.getPlace()));
        address.setComplement(CharacterUtil.convertFirstUppercaseCharacter(address.getComplement()));
        address.setDistrict(CharacterUtil.convertFirstUppercaseCharacter(address.getDistrict()));
        address.setCity(CharacterUtil.convertFirstUppercaseCharacter(address.getCity()));
        address.setState(CharacterUtil.convertAllUppercaseCharacters(address.getState()));
        address.setZipCode(CharacterUtil.removeSpecialCharacters(address.getZipCode()));

        var contact = person.getContact();
        contact.setEmail(CharacterUtil.convertAllLowercaseCharacters(contact.getEmail()));
        contact.setPhone(CharacterUtil.removeSpecialCharacters(contact.getPhone()));

        var user = person.getUser();
        user.setUsername(CharacterUtil.convertAllLowercaseCharacters(user.getUsername()));

        var permissions = user.getPermissions().stream().map(this::converterPermission).toList();
        user.setPermissions(permissions);

        person.setAddress(address);
        person.setContact(contact);
        person.setUser(user);

    }

    private Permission converterPermission(Permission permission) {
        permission.setName(CharacterUtil.convertAllLowercaseCharacters(permission.getName()));
        permission.setSystem(this.converterSystems(permission.getSystem()));
        return permission;
    }

    private Systems converterSystems(Systems systems) {
        if (Objects.nonNull(systems)) {
            systems.setName(CharacterUtil.convertAllLowercaseCharacters(systems.getName()));
        }
        return systems;
    }


}
