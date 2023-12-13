package com.fmatheus.app.controller.dto.response.extension;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsReadResponse {
    private UUID uuid;
    private String name;
    private String description;
}
