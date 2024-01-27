package com.fmatheus.app.controller.dto.request.base;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateBase {

    @NotNull
    @Size(max = 70)
    private String place;

    @NotNull
    @Size(max = 10)
    private String number;

    @Size(max = 50)
    private String complement;

    @NotNull
    @Size(max = 70)
    private String district;

    @NotNull
    @Size(max = 70)
    private String city;

    @NotNull
    @Size(min = 2, max = 2)
    private String state;

    @NotNull
    @Size(max = 15)
    private String zipCode;


}
