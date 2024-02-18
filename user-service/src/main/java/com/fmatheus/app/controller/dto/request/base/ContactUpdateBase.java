package com.fmatheus.app.controller.dto.request.base;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdateBase {

    @NotNull
    @Size(max = 80)
    @Email
    private String email;

    @NotNull
    @Size(max = 15)
    private String phone;

}