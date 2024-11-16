package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.application.domain.PersonDomain;
import com.fmatheus.app.infra.adapter.input.converter.PersonConverter;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class PersonConverterImpl implements PersonConverter {

    private final ModelMapper mapper;

    @Override
    public PersonDomain converterToEntity(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDtoResponse converterToResponse(PersonDomain person) {
        return this.mapper.map(person, UserDtoResponse.class);
    }

}
