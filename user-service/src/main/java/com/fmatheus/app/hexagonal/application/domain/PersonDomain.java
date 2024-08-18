package com.fmatheus.app.hexagonal.application.domain;


import static com.fmatheus.app.hexagonal.application.util.AppUtil.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;


public class PersonDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950200934L;

    private Integer id;
    private String name;
    private String document;
    private LocalDateTime createdAt;
    private AddressDomain address;
    private PersonTypeDomain personType;
    private ContactDomain contact;
    private Collection<UserDomain> users;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return convertFirstUppercaseCharacter(removeDuplicateSpace(removeAccents(this.name)));
    }

    public void setName(String name) {
        this.name = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(name)));
    }

    public String getDocument() {
        return removeSpecialCharacters(document);
    }

    public void setDocument(String document) {
        this.document = removeSpecialCharacters(document);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AddressDomain getAddress() {
        return address;
    }

    public void setAddress(AddressDomain address) {
        this.address = address;
    }

    public PersonTypeDomain getPersonType() {
        return personType;
    }

    public void setPersonType(PersonTypeDomain personType) {
        this.personType = personType;
    }

    public ContactDomain getContact() {
        return contact;
    }

    public void setContact(ContactDomain contact) {
        this.contact = contact;
    }

    public Collection<UserDomain> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDomain> users) {
        this.users = users;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonDomain that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
