package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.AddressConverter;
import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonConverterImpl implements PersonConverter {

    private final ModelMapper mapper;
    private final AddressConverter addressConverter;

    @Override
    public Person converterToEntity(Object o) {
        return null;
    }

    @Override
    public PersonResponse converterToResponse(Person person) {
        person.setName(CharacterUtil.convertFirstUppercaseCharacter(person.getName()));
        this.addressConverter.converterToResponse(person.getAddress());
        return this.mapper.map(person, PersonResponse.class);
    }
}
