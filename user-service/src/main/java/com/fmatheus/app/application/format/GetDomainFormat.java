package com.fmatheus.app.application.format;

import com.fmatheus.app.application.domain.*;

import java.util.Collection;

import static com.fmatheus.app.application.util.AppUtil.*;
import static com.fmatheus.app.application.util.AppUtil.removeSpecialCharacters;

public class GetDomainFormat {


    private GetDomainFormat() {
        throw new IllegalStateException(getClass().getName());
    }

    public static PersonDomain getPersonDomain(PersonDomain person) {
        person.setName(removeDuplicateSpace(convertFirstUppercaseCharacter(person.getName())));
        return person;
    }

    public static UserDomain getUserDomain(UserDomain user) {
        ProfileDomain profile = getProfileDomain(user.getProfile());
        user.setUsername(convertAllLowercaseCharacters(user.getUsername()));
        user.setProfile(profile);
        return user;
    }


    public static AddressDomain getAddressDomain(AddressDomain address) {
        if (address != null) {
            address.setPlace(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getPlace())));
            address.setComplement(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getComplement())));
            address.setDistrict(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getDistrict())));
            address.setCity(removeDuplicateSpace(convertFirstUppercaseCharacter(address.getCity())));
            address.setState(removeDuplicateSpace(convertAllUppercaseCharacters(address.getState())));
            address.setZipCode(removeSpecialCharacters(address.getZipCode()));
        }
        return address;
    }

    public static PersonTypeDomain getPersonTypeDomain(PersonTypeDomain personType) {
        if (personType != null) {
            personType.setName(removeDuplicateSpace(convertFirstUppercaseCharacter(personType.getName())));
        }
        return personType;
    }

    public static ContactDomain getContactDomain(ContactDomain contact) {
        if (contact != null) {
            contact.setEmail(convertAllLowercaseCharacters(contact.getEmail()));
            contact.setPhone(removeSpecialCharacters(contact.getPhone()));
        }
        return contact;
    }

    public static PermissionDomain getPermissionDomain(PermissionDomain permission) {
        if (permission != null) {
            SystemsDomain system = getSystemsDomain(permission.getSystem());
            permission.setName(convertAllLowercaseCharacters(permission.getName()));
            permission.setSystem(system);
        }
        return permission;
    }

    public static SystemsDomain getSystemsDomain(SystemsDomain system) {
        if (system != null) {
            system.setName(convertAllLowercaseCharacters(system.getName()));
            system.setDescription(convertFirstUppercaseCharacter(system.getDescription()));
        }
        return system;
    }

    public static ProfileDomain getProfileDomain(ProfileDomain profile) {
        if (profile != null) {
            profile.setName(convertFirstUppercaseCharacter(profile.getName()));
        }
        return profile;
    }

    public static UserSessionsDomain getUserSessionsDomain(UserSessionsDomain userSessions) {
        if (userSessions != null) {
            userSessions.setCity(convertFirstUppercaseCharacter(userSessions.getCity()));
            userSessions.setState(convertAllUppercaseCharacters(userSessions.getState()));
            userSessions.setStatus(convertFirstUppercaseCharacter(userSessions.getStatus()));
            userSessions.setMessage(convertFirstUppercaseCharacter(userSessions.getMessage()));
        }
        return userSessions;
    }

}
