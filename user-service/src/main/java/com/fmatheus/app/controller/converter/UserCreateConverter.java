package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.controller.dto.response.UserDtoResponse;
import com.fmatheus.app.model.entity.Person;

public interface UserCreateConverter extends MapperConverter<Person, UserCreateDtoRequest, UserDtoResponse> {
}
