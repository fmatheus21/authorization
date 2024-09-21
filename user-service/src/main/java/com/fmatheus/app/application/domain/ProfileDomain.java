package com.fmatheus.app.application.domain;

import com.fmatheus.app.application.util.AppUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


public class ProfileDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950244234L;

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return AppUtil.convertFirstUppercaseCharacter(name);
    }

    public void setName(String name) {
        this.name = AppUtil.convertAllUppercaseCharacters(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfileDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
