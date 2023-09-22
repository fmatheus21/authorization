package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.*;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserPartialConverterImpl implements UserPartialConverter {

    private final ModelMapper mapper;
    private final PersonConverter personConverter;

    @Override
    public User converterToEntity(Object o) {
        return null;
    }

    @Override
    public UserResponse converterToResponse(User user) {
        this.personConverter.converterToResponse(user.getPerson());
        return this.mapper.map(user, UserResponse.class);
    }


}
