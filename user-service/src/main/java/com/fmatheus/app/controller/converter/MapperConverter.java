package com.fmatheus.app.controller.converter;

public interface MapperConverter<T, S, U> {
    T converterToEntity(S s);

    U converterToResponse(T t);
}