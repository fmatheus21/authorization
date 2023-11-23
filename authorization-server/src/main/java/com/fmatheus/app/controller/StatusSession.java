package com.fmatheus.app.controller;

import lombok.Getter;

@Getter
public enum StatusSession {

    SUCCESS("SUCESSO"),
    ACCESS_DENIED("USUARIO OU SENHA INCORRETO"),
    UNAUTHORIZED("SEM PREMISSAO PARA O SISTEMA");

    private final String value;

    StatusSession(String value) {
        this.value = value;
    }
}