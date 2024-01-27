package com.fmatheus.app.controller.dto.request.base;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateBase {

    @NotNull
    private Integer id;

    @NotNull
    private String name;
}
