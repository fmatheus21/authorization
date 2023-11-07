package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.UserCreateRequest;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.model.entity.Person;

public interface UserCreateConverter extends MapperConverter<Person, UserCreateRequest, PersonResponse> {
}
