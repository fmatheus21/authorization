package com.fmatheus.app.infra.adapter.input.facade;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SystemsFacade {

    /*private final SystemsService systemsService;
    private final SystemsConverter systemsConverter;
    private final MessageResponse messageResponse;

    public SystemsDtoResponse findByUuid(UUID uuid) {
        var response = this.systemsService.findByUuid(uuid).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.systemsConverter.converterToResponse(response);
    }*/

}
