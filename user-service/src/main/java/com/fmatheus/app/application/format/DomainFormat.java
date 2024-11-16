package com.fmatheus.app.application.format;

import com.fmatheus.app.application.domain.*;

import java.util.Collection;

import static com.fmatheus.app.application.util.AppUtil.*;

public class DomainFormat {

    private DomainFormat() {
        throw new IllegalStateException(getClass().getName());
    }

    public static UserDomain getUserDomainFormat(UserDomain user) {
        PersonDomain person = getPersonDomain(user.getPerson());
        AddressDomain address = getAddressDomain(person.getAddress());
        PersonTypeDomain personType = getPersonTypeDomain(person.getPersonType());
        ContactDomain contact = getContactDomain(person.getContact());
        ProfileDomain profile = getProfileDomain(user.getProfile());
        Collection<PermissionDomain> permissions = user.getPermissions().stream().map(DomainFormat::getPermissionDomain).toList();
        Collection<UserSessionsDomain> userSessions = user.getUserSessions().stream().map(DomainFormat::getUserSessionsDomain).toList();

        person.setAddress(address);
        person.setPersonType(personType);
        person.setContact(contact);

        user.setUsername(convertAllLowercaseCharacters(user.getUsername()));
        user.setPerson(person);
        user.setProfile(profile);
        user.setPermissions(permissions);
        user.setUserSessions(userSessions);
        return user;
    }

    private static PersonDomain getPersonDomain(PersonDomain person) {
        person.setName(removeDuplicateSpace(convertFirstUppercaseCharacter(person.getName())));
        return person;
    }

    private static AddressDomain getAddressDomain(AddressDomain address) {
        address.setPlace(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getPlace())));
        address.setComplement(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getComplement())));
        address.setDistrict(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getDistrict())));
        address.setCity(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getCity())));
        address.setState(removeDuplicateSpace(convertAllUppercaseCharacters(address.getState())));
        address.setZipCode(removeSpecialCharacters(address.getZipCode()));
        return address;
    }

    private static PersonTypeDomain getPersonTypeDomain(PersonTypeDomain personType) {
        personType.setName(removeDuplicateSpace(convertFirstUppercaseCharacter(personType.getName())));
        return personType;
    }

    private static ContactDomain getContactDomain(ContactDomain contact) {
        contact.setEmail(convertAllLowercaseCharacters(contact.getEmail()));
        contact.setPhone(removeSpecialCharacters(contact.getPhone()));
        return contact;
    }

    private static PermissionDomain getPermissionDomain(PermissionDomain permission) {
        SystemsDomain system = permission.getSystem();
        system.setName(convertAllLowercaseCharacters(system.getName()));
        system.setDescription(convertFirstUppercaseCharacter(system.getDescription()));
        permission.setName(convertAllLowercaseCharacters(permission.getName()));
        permission.setSystem(system);
        return permission;
    }

    private static ProfileDomain getProfileDomain(ProfileDomain profile) {
        profile.setName(convertFirstUppercaseCharacter(profile.getName()));
        return profile;
    }

    private static UserSessionsDomain getUserSessionsDomain(UserSessionsDomain userSessions) {
        userSessions.setCity(convertFirstUppercaseCharacter(userSessions.getCity()));
        userSessions.setState(convertAllUppercaseCharacters(userSessions.getState()));
        userSessions.setStatus(convertFirstUppercaseCharacter(userSessions.getStatus()));
        userSessions.setMessage(convertFirstUppercaseCharacter(userSessions.getMessage()));
        return userSessions;
    }
}
