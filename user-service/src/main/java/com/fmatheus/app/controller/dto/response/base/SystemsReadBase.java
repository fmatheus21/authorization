package com.fmatheus.app.controller.dto.response.base;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsReadBase {
    private UUID uuid;
    private String name;
    private String description;
}
