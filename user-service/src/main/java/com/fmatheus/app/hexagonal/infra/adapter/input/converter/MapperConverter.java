package com.fmatheus.app.hexagonal.infra.adapter.input.converter;

public interface MapperConverter<T, S, U> {
    T converterToEntity(S s);

    U converterToResponse(T t);
}