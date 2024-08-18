package com.fmatheus.app.hexagonal.application.domain;

import static com.fmatheus.app.hexagonal.application.util.AppUtil.*;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AddressDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    private Integer id;
    private String place;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;
    private String zipCode;
    private PersonDomain person;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlace() {
        return convertFirstUppercaseCharacter(removeDuplicateSpace(removeAccents(this.place)));
    }

    public void setPlace(String place) {
        this.place = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(place)));
    }

    public String getNumber() {
        return convertFirstUppercaseCharacter(this.number);
    }

    public void setNumber(String number) {
        this.number = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(number)));
    }

    public String getComplement() {
        return convertFirstUppercaseCharacter(removeDuplicateSpace(removeAccents(this.complement)));
    }

    public void setComplement(String complement) {
        this.complement = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(complement)));
    }

    public String getDistrict() {
        return convertFirstUppercaseCharacter(removeDuplicateSpace(removeAccents(this.district)));
    }

    public void setDistrict(String district) {
        this.district = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(district)));
    }

    public String getCity() {
        return convertFirstUppercaseCharacter(removeDuplicateSpace(removeAccents(this.city)));
    }

    public void setCity(String city) {
        this.city = convertAllUppercaseCharacters(removeDuplicateSpace(removeAccents(city)));
    }

    public String getState() {
        return convertAllUppercaseCharacters(this.state);
    }

    public void setState(String state) {
        this.state = convertAllUppercaseCharacters(state);
    }

    public String getZipCode() {
        return removeSpecialCharacters(zipCode);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = removeSpecialCharacters(zipCode);
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
        if (!(o instanceof AddressDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


    @Override
    public String toString() {
        return "AddressDomain{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", person=" + person +
                '}';
    }
}
