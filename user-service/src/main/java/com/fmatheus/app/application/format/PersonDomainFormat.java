package com.fmatheus.app.application.format;

import com.fmatheus.app.application.domain.*;

import java.util.Collection;

import static com.fmatheus.app.application.format.GetDomainFormat.*;
import static com.fmatheus.app.application.format.SetDomainFormat.*;
import static com.fmatheus.app.application.util.AppUtil.*;

public class PersonDomainFormat {

    private PersonDomainFormat() {
        throw new IllegalStateException(getClass().getName());
    }

    public static PersonDomain getPersonDomainFormat(PersonDomain person) {
        person.setName(removeDuplicateSpace(convertFirstUppercaseCharacter(person.getName())));

        PersonTypeDomain personType = getPersonTypeDomain(person.getPersonType());
        AddressDomain address = getAddressDomain(person.getAddress());
        ContactDomain contact = getContactDomain(person.getContact());
        Collection<UserDomain> users = person.getUsers() != null && !person.getUsers().isEmpty() ? person.getUsers().stream().map(GetDomainFormat::getUserDomain).toList() : null;

        person.setPersonType(personType);
        person.setAddress(address);
        person.setContact(contact);
        person.setUsers(users);
        return person;
    }

    public static PersonDomain setPersonDomainFormat(PersonDomain person) {

        person.setName(removeDuplicateSpace(convertAllUppercaseCharacters(person.getName())));

        AddressDomain address = setAddressDomain(person.getAddress());
        ContactDomain contact = setContactDomain(person.getContact());
        Collection<UserDomain> users = person.getUsers() != null && !person.getUsers().isEmpty() ? person.getUsers().stream().map(SetDomainFormat::setUserDomain).toList() : null;

        person.setAddress(address);
        person.setContact(contact);
        person.setUsers(users);
        return person;
    }


}
