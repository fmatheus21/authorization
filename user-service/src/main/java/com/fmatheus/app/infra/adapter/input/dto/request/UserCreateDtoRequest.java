package com.fmatheus.app.infra.adapter.input.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDtoRequest {

    @NotNull
    @Size(max = 70)
    private String name;

    @NotNull
    @Size(max = 20)
    private String document;

    @Valid
    @NotNull
    private PersonTypeRequest personType;

    @Valid
    @NotNull
    private AddressRequest address;

    @Valid
    @NotNull
    private ContactRequest contact;

    @Valid
    @NotEmpty
    private Collection<PermissionRequest> permissions;


    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonTypeRequest {

        @NotNull
        private int id;

        @NotNull
        private UUID uuid;

        @NotNull
        private String name;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressRequest {

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

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermissionRequest {

        @NotNull
        private Integer id;

        @NotNull
        private String name;
    }

}
