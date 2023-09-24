package com.fmatheus.app.controller.dto.request.extension;

import jakarta.validation.constraints.NotNull;
import lombok.*;


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
