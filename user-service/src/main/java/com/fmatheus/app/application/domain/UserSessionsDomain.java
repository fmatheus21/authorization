package com.fmatheus.app.application.domain;

import com.fmatheus.app.application.util.AppUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserSessionsDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950290045L;

    private Integer id;
    private String zipCode;
    private String place;
    private String district;
    private String city;
    private String state;
    private LocalDateTime date;
    private String status;
    private String message;
    private UserDomain user;
    private SystemsDomain system;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZipCode() {
        return AppUtil.removeSpecialCharacters(this.zipCode);
    }

    public void setZipCode(String zipCode) {
        this.zipCode = AppUtil.removeSpecialCharacters(zipCode);
    }

    public String getPlace() {
        return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(this.place)));
    }

    public void setPlace(String place) {
        this.place = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(AppUtil.removeSpecialCharacters(place)));
    }

    public String getDistrict() {
        return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(this.district)));
    }

    public void setDistrict(String district) {
        this.district = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(district)));
    }

    public String getCity() {
        return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(this.city)));
    }

    public void setCity(String city) {
        this.city = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(city)));
    }

    public String getState() {
        return AppUtil.convertAllUppercaseCharacters(this.state);
    }

    public void setState(String state) {
        this.state = AppUtil.convertAllUppercaseCharacters(state);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return AppUtil.convertFirstUppercaseCharacter(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(this.message)));
    }

    public void setMessage(String message) {
        this.message = AppUtil.convertAllUppercaseCharacters(AppUtil.removeDuplicateSpace(AppUtil.removeAccents(message)));
    }

    public UserDomain getUser() {
        return user;
    }

    public void setUser(UserDomain user) {
        this.user = user;
    }

    public SystemsDomain getSystem() {
        return system;
    }

    public void setSystem(SystemsDomain system) {
        this.system = system;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSessionsDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
