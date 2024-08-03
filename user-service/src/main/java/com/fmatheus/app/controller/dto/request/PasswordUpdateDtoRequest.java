package com.fmatheus.app.controller.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordUpdateDtoRequest {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 12)
    private String password;

    @NotNull
    @NotBlank
    private String confirmPassword;

}
