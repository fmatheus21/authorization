package com.fmatheus.app.controller.dto.response.extension;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsResponse {
    private UUID uuid;
    private String name;
}
