package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.response.PersonPartialResponse;
import com.fmatheus.app.model.entity.Person;

public interface PersonPartialConverter extends MapperConverter<Person, Object, PersonPartialResponse> {
}
