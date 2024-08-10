package com.fmatheus.app.hexagonal.application.domain;

import com.fmatheus.app.hexagonal.application.util.AppUtil;


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
        return AppUtil.convertFirstUppercaseCharacter(this.place);
    }

    public void setPlace(String place) {
        AppUtil.isNotNull("place", place);
        this.place = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(place));
    }

    public String getNumber() {
        return AppUtil.convertFirstUppercaseCharacter(this.number);
    }

    public void setNumber(String number) {
        AppUtil.isNotNull("number", number);
        this.number = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(number));
    }

    public String getComplement() {
        return AppUtil.convertFirstUppercaseCharacter(this.complement);
    }

    public void setComplement(String complement) {
        this.complement = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(complement));
    }

    public String getDistrict() {
        return AppUtil.convertFirstUppercaseCharacter(this.district);
    }

    public void setDistrict(String district) {
        AppUtil.isNotNull("district", district);
        this.district = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(district));
    }

    public String getCity() {
        return AppUtil.convertFirstUppercaseCharacter(this.city);
    }

    public void setCity(String city) {
        AppUtil.isNotNull("city", city);
        this.city = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(city));
    }

    public String getState() {
        return AppUtil.convertFirstUppercaseCharacter(this.state);
    }

    public void setState(String state) {
        AppUtil.isNotNull("state", state);
        this.state = AppUtil.convertAllUppercaseCharacters(AppUtil.removeSpecialCharacters(state));
    }

    public String getZipCode() {
        return AppUtil.removeSpecialCharacters(zipCode);
    }

    public void setZipCode(String zipCode) {
        AppUtil.isNotNull("zipCode", zipCode);
        this.zipCode = AppUtil.removeSpecialCharacters(zipCode);
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
