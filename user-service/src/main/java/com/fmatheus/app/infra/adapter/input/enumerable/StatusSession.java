package com.fmatheus.app.infra.adapter.input.enumerable;

import lombok.Getter;

@Getter
public enum StatusSession {

    SUCCESS("SUCESSO"),
    ACCESS_DENIED("USUARIO OU SENHA INCORRETO"),
    UNAUTHORIZED("SEM AUTORIZAÇÃO PARA O SISTEMA");

    private final String value;

    StatusSession(String value) {
        this.value = value;
    }
}
