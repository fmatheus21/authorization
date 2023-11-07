package com.fmatheus.app.controller.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.response.extension.SystemsReadResponse;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsResponse {
    private UUID uuid;
    private String name;
    private Collection<SystemsReadResponse> systems;
}
