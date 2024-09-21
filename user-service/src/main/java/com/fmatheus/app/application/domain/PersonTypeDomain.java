package com.fmatheus.app.application.domain;


import static com.fmatheus.app.application.util.AppUtil.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class PersonTypeDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950233521L;

    private Integer id;
    private UUID uuid;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return convertFirstUppercaseCharacter(name);
    }

    public void setName(String name) {
        this.name = convertAllUppercaseCharacters(name);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonTypeDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
