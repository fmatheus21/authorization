package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.SystemConverter;
import com.fmatheus.app.controller.dto.response.SystemsResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Systems;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class SystemConverterImpl implements SystemConverter {

    private final ModelMapper mapper;

    @Override
    public Systems converterToEntity(Object o) {
        return null;
    }

    @Override
    public SystemsResponse converterToResponse(Systems systems) {
        systems.setName(CharacterUtil.convertAllLowercaseCharacters(systems.getName()));
        return this.mapper.map(systems, SystemsResponse.class);
    }
}
