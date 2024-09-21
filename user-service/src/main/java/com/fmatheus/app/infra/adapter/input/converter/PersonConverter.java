package com.fmatheus.app.infra.adapter.input.converter;


import com.fmatheus.app.application.domain.PersonDomain;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;

public interface PersonConverter extends MapperConverter<PersonDomain, Object, UserDtoResponse> {
}
