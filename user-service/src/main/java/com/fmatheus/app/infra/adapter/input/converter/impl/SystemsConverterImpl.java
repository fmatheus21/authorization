package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.infra.adapter.input.converter.SystemsConverter;
import com.fmatheus.app.infra.adapter.input.dto.response.SystemsDtoResponse;
import com.fmatheus.app.infra.adapter.input.util.CharacterUtil;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Permission;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Systems;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SystemsConverterImpl implements SystemsConverter {

    private final ModelMapper mapper;

    @Override
    public Systems converterToEntity(Object o) {
        return null;
    }

    @Override
    public SystemsDtoResponse converterToResponse(Systems systems) {
        var converter = this.mapper.map(systems, SystemsDtoResponse.class);
        converter.setName(CharacterUtil.convertAllLowercaseCharacters(converter.getName()));
        var permissions = systems.getPermissions().stream().map(this::converterPermissions).toList();
        converter.setPermissions(permissions);
        return converter;
    }

    private SystemsDtoResponse.PermissionResponse converterPermissions(Permission permission) {
        return SystemsDtoResponse.PermissionResponse.builder()
                .uuid(permission.getUuid())
                .name(CharacterUtil.convertAllLowercaseCharacters(permission.getName()))
                .build();
    }

}
