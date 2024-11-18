package com.fmatheus.app.application.format;

import com.fmatheus.app.application.domain.*;

import static com.fmatheus.app.application.util.AppUtil.*;
import static com.fmatheus.app.application.util.AppUtil.convertAllUppercaseCharacters;

public class SetDomainFormat {

    private SetDomainFormat() {
        throw new IllegalStateException(getClass().getName());
    }

    public static PersonDomain setPersonDomain(PersonDomain person) {
        person.setName(removeDuplicateSpace(convertAllUppercaseCharacters(person.getName())));
        return person;
    }


    public static AddressDomain setAddressDomain(AddressDomain address) {
        address.setPlace(removeDuplicateSpace(convertAllUppercaseCharacters(address.getPlace())));
        address.setComplement(removeDuplicateSpace(convertAllUppercaseCharacters(address.getComplement())));
        address.setDistrict(removeDuplicateSpace(convertAllUppercaseCharacters(address.getDistrict())));
        address.setCity(removeDuplicateSpace(convertAllUppercaseCharacters(address.getCity())));
        address.setState(removeDuplicateSpace(convertAllUppercaseCharacters(address.getState())));
        address.setZipCode(removeSpecialCharacters(address.getZipCode()));
        return address;
    }

    public static ContactDomain setContactDomain(ContactDomain contact) {
        contact.setEmail(convertAllUppercaseCharacters(contact.getEmail()));
        contact.setPhone(removeSpecialCharacters(contact.getPhone()));
        return contact;
    }

    public static UserDomain setUserDomain(UserDomain user) {
        var userSessions = user.getUserSessions() != null ? user.getUserSessions().stream().map(SetDomainFormat::setUserSessionsDomain).toList() : null;
        user.setUsername(removeSpecialCharacters(user.getUsername()));
        user.setUserSessions(userSessions);
        return user;
    }

    public static UserSessionsDomain setUserSessionsDomain(UserSessionsDomain userSessions) {
        userSessions.setCity(convertAllUppercaseCharacters(userSessions.getCity()));
        userSessions.setState(convertAllUppercaseCharacters(userSessions.getState()));
        userSessions.setStatus(convertAllUppercaseCharacters(userSessions.getStatus()));
        userSessions.setMessage(convertAllUppercaseCharacters(userSessions.getMessage()));
        return userSessions;
    }
}
