package com.fmatheus.app.controller.dto.request.create;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateRequest{

    @NotNull
    private Integer id;

    @NotNull
    private String name;
}
