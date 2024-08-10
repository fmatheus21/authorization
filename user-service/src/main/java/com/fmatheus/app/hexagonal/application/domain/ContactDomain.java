package com.fmatheus.app.hexagonal.application.domain;

import com.fmatheus.app.hexagonal.application.util.AppUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


public class ContactDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950283345L;

    private Integer id;
    private String email;
    private String phone;
    private PersonDomain person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return AppUtil.convertAllLowercaseCharacters(email);
    }

    public void setEmail(String email) {
        this.email = AppUtil.convertFirstUppercaseCharacter(email);
    }

    public String getPhone() {
        return AppUtil.removeSpecialCharacters(phone);
    }

    public void setPhone(String phone) {
        this.phone = AppUtil.removeSpecialCharacters(phone);
    }

    public PersonDomain getPerson() {
        return person;
    }

    public void setPerson(PersonDomain person) {
        this.person = person;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContactDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
