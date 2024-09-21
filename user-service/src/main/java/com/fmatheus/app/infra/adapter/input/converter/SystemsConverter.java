package com.fmatheus.app.infra.adapter.input.converter;


import com.fmatheus.app.infra.adapter.input.dto.response.SystemsDtoResponse;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Systems;

public interface SystemsConverter extends MapperConverter<Systems, Object, SystemsDtoResponse> {
}
