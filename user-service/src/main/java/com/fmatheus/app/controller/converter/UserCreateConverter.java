package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.create.UserCreateRequest;
import com.fmatheus.app.controller.dto.response.create.UserCreateResponse;
import com.fmatheus.app.model.entity.Person;

public interface UserCreateConverter extends MapperConverter<Person, UserCreateRequest, UserCreateResponse> {
}
