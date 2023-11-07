package com.fmatheus.app.controller.dto.response.extension;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionReadResponse {
    private UUID uuid;
    private String name;
    private SystemsReadResponse system;
}
