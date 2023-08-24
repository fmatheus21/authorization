package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.*;
import com.fmatheus.app.controller.dto.response.UserPartialResponse;
import com.fmatheus.app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserPartialConverterImpl implements UserPartialConverter {

    private final ModelMapper mapper;
    private final PersonPartialConverter personConverter;
    private final ContactConverter contactConverter;

    @Override
    public User converterToEntity(Object o) {
        return null;
    }

    @Override
    public UserPartialResponse converterToResponse(User user) {
        var response = this.mapper.map(user, UserPartialResponse.class);
        var person = this.personConverter.converterToResponse(user.getPerson());
        var contact = this.contactConverter.converterToResponse(user.getPerson().getContact());
        response.setPerson(person);
        response.setContact(contact);
        return response;
    }


}
