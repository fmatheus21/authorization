package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.UserCreateRequest;
import com.fmatheus.app.controller.dto.response.UserCreateResponse;
import com.fmatheus.app.model.entity.Person;

public interface UserCreateConverter extends MapperConverter<Person, UserCreateRequest, UserCreateResponse> {
}
