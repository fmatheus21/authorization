package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.ParmissionConverter;
import com.fmatheus.app.controller.converter.SystemConverter;
import com.fmatheus.app.controller.dto.response.PermissionResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Permission;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ParmissionConverterImpl implements ParmissionConverter {

    private final ModelMapper mapper;
    private final SystemConverter systemConverter;

    @Override
    public Permission converterToEntity(Object o) {
        return null;
    }

    @Override
    public PermissionResponse converterToResponse(Permission permission) {
        permission.setName(CharacterUtil.convertAllLowercaseCharacters(permission.getName()));
        var response = this.mapper.map(permission, PermissionResponse.class);
        var system = this.systemConverter.converterToResponse(permission.getSystem());
        response.setSystem(system);
        return response;
    }
}
