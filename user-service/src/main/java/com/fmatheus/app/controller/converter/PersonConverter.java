package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.response.UserReadResponse;
import com.fmatheus.app.model.entity.Person;

public interface PersonConverter extends MapperConverter<Person, Object, UserReadResponse> {
}
