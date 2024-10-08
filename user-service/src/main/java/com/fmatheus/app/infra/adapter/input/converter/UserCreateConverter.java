package com.fmatheus.app.infra.adapter.input.converter;


import com.fmatheus.app.application.domain.PersonDomain;
import com.fmatheus.app.infra.adapter.input.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Person;

public interface UserCreateConverter extends MapperConverter<PersonDomain, UserCreateDtoRequest, UserDtoResponse> {
}
