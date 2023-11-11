package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.SystemsConverter;
import com.fmatheus.app.controller.dto.response.SystemsResponse;
import com.fmatheus.app.controller.exception.message.MessageResponse;
import com.fmatheus.app.model.service.SystemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SystemsRule {

    private final SystemsService systemsService;
    private final SystemsConverter systemsConverter;
    private final MessageResponse messageResponse;

    public SystemsResponse findByUuid(UUID uuid) {
        var response = this.systemsService.findByUuid(uuid).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.systemsConverter.converterToResponse(response);
    }

}
