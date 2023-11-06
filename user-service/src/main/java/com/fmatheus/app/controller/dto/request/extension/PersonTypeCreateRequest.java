package com.fmatheus.app.controller.dto.request.extension;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonTypeCreateRequest {

    @NotNull
    private int id;

    @NotNull
    private UUID uuid;

    @NotNull
    private String name;
}
