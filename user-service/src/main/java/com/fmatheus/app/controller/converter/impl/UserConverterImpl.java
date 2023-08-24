package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.AddressConverter;
import com.fmatheus.app.controller.converter.ParmissionConverter;
import com.fmatheus.app.controller.converter.PersonConverter;
import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserConverterImpl implements UserConverter {

    private final ModelMapper mapper;
    private final ParmissionConverter parmissionConverter;
    private final PersonConverter personConverter;
    private final AddressConverter addressConverter;

    @Override
    public User converterToEntity(Object o) {
        return null;
    }

    @Override
    public UserResponse converterToResponse(User user) {
        var response = this.mapper.map(user, UserResponse.class);
        var person = this.personConverter.converterToResponse(user.getPerson());
        var address = this.addressConverter.converterToResponse(user.getPerson().getAddress());
        var permissions = user.getPermissions().stream().map(this.parmissionConverter::converterToResponse).toList();
        response.setPerson(person);
        response.setAddress(address);
        response.setPermissions(permissions);
        return response;
    }


}
