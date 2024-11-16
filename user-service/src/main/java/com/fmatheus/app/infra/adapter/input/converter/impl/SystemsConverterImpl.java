package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.infra.adapter.input.converter.SystemsConverter;
import com.fmatheus.app.infra.adapter.input.dto.response.SystemsDtoResponse;
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
        return this.mapper.map(systems, SystemsDtoResponse.class);
    }

}
