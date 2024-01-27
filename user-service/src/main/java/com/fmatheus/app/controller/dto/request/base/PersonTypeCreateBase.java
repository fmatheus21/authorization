package com.fmatheus.app.controller.dto.request.base;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonTypeCreateBase {

    @NotNull
    private int id;

    @NotNull
    private UUID uuid;

    @NotNull
    private String name;
}
