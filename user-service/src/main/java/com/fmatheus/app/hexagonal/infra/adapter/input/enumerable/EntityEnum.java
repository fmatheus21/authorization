package com.fmatheus.app.hexagonal.infra.adapter.input.enumerable;

import lombok.Getter;

@Getter
public enum EntityEnum {

    ID("id"),
    NAME("name"),
    DOCUMENT("document"),
    EMAIL("email"),
    USERNAME("username"),
    ID_PERSON("person"),
    CONTACT("contact");


    private final String value;

    EntityEnum(String value) {
        this.value = value;
    }

}
