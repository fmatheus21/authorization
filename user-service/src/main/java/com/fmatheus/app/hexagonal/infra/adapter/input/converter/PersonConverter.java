package com.fmatheus.app.hexagonal.infra.adapter.input.converter;


import com.fmatheus.app.hexagonal.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Person;

public interface PersonConverter extends MapperConverter<Person, Object, UserDtoResponse> {
}
