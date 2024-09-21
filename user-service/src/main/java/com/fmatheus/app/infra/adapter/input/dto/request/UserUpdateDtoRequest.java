package com.fmatheus.app.infra.adapter.input.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDtoRequest {

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String name;

    @Valid
    private AddressRequest address;

    @Valid
    private ContactRequest contact;


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressRequest {

        @NotNull
        @NotBlank
        @Size(max = 70)
        private String place;

        @NotNull
        @NotBlank
        @Size(max = 10)
        private String number;

        @Size(max = 50)
        private String complement;

        @NotNull
        @NotBlank
        @Size(max = 70)
        private String district;

        @NotNull
        @NotBlank
        @Size(max = 70)
        private String city;

        @NotNull
        @NotBlank
        @Size(min = 2, max = 2)
        private String state;

        @NotNull
        @NotBlank
        @Size(max = 15)
        private String zipCode;
    }


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactRequest {

        @NotNull
        @Size(max = 80)
        @Email
        private String email;

        @NotNull
        @Size(max = 15)
        private String phone;

    }


}
