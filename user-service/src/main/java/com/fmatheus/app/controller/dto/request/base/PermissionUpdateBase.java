package com.fmatheus.app.controller.dto.request.base;


import com.fmatheus.app.controller.enumerable.MethodEnum;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionUpdateBase {

    @NotNull
    private Integer id;

    @NotNull
    private MethodEnum method;
}
