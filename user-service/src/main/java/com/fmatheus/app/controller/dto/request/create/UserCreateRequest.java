package com.fmatheus.app.controller.dto.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @Valid
    @NotNull
    private PersonCreateRequest person;
}
