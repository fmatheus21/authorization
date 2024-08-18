package com.fmatheus.app.hexagonal.infra.adapter.input.converter.impl;

import com.fmatheus.app.hexagonal.application.domain.PersonDomain;
import com.fmatheus.app.hexagonal.infra.adapter.input.converter.PersonConverter;
import com.fmatheus.app.hexagonal.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.hexagonal.infra.adapter.input.util.CharacterUtil;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Objects;


@RequiredArgsConstructor
@Component
public class PersonConverterImpl implements PersonConverter {

    private final ModelMapper mapper;

    @Override
    public PersonDomain converterToEntity(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDtoResponse converterToResponse(PersonDomain person) {
        //this.converterPerson(person);
        return this.mapper.map(person, UserDtoResponse.class);
    }

    /*private void converterPerson(PersonDomain person) {
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

        //var users = person.getUsers().stream().map(this::converterUser).toList();

        person.setAddress(address);
        person.setContact(contact);
        //person.setUsers(users);


    }*/

    private User converterUser(User user) {
        user.setUsername(CharacterUtil.convertAllLowercaseCharacters(user.getUsername()));
        var profile = this.convertProfila(user.getProfile());
        var permissions = user.getPermissions().stream().map(this::converterPermission).toList();
        user.setPermissions(permissions);
        var userSessions = user.getUserSessions().stream().map(this::converterUserSessions)
                .sorted(Comparator.comparing(UserSessions::getDate).reversed())
                .limit(10)
                .toList();
        user.setUserSessions(userSessions);
        user.setProfile(profile);
        return user;
    }

    private Permission converterPermission(Permission permission) {
        permission.setName(CharacterUtil.convertAllLowercaseCharacters(permission.getName()));
        permission.setSystem(this.converterSystems(permission.getSystem()));
        return permission;
    }

    private Profile convertProfila(Profile profile) {
        profile.setName(CharacterUtil.convertFirstUppercaseCharacter(profile.getName()));
        return profile;
    }

    private UserSessions converterUserSessions(UserSessions userSessions) {
        userSessions.setCity(CharacterUtil.convertFirstUppercaseCharacter(userSessions.getCity()));
        userSessions.setMessage(CharacterUtil.convertFirstUppercaseCharacter(userSessions.getMessage()));
        userSessions.setMessage(CharacterUtil.convertFirstUppercaseCharacter(userSessions.getMessage()));
        userSessions.getSystem().setDescription(CharacterUtil.convertFirstUppercaseCharacter(userSessions.getSystem().getDescription()));
        return userSessions;
    }

    private Systems converterSystems(Systems systems) {
        if (Objects.nonNull(systems)) {
            systems.setName(CharacterUtil.convertAllLowercaseCharacters(systems.getName()));
        }
        return systems;
    }


}
