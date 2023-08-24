package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.PersonPartialConverter;
import com.fmatheus.app.controller.dto.response.PersonPartialResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonPartialConverterImpl implements PersonPartialConverter {

    private final ModelMapper mapper;

    @Override
    public Person converterToEntity(Object o) {
        return null;
    }

    @Override
    public PersonPartialResponse converterToResponse(Person person) {
        person.setName(CharacterUtil.convertFirstUppercaseCharacter(person.getName()));
        return this.mapper.map(person, PersonPartialResponse.class);
    }
}
