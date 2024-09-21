package com.fmatheus.app.infra.adapter.input.enumerable;

import lombok.Getter;

@Getter
public enum MethodEnum {

    GET("get"),
    POST("post"),
    PUT("put"),
    DELETE("delete");


    private final String value;

    MethodEnum(String value) {
        this.value = value;
    }

}
