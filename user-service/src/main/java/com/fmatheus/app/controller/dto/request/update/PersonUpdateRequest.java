package com.fmatheus.app.controller.dto.request.update;

import jakarta.validation.Valid;
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
public class PersonUpdateRequest {


    @NotNull
    @NotBlank
    @Size(max = 70)
    private String name;

    @Valid
    private AddressUpdateRequest address;

    @Valid
    private ContactUpdateRequest contact;
}
