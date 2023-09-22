package com.fmatheus.app.controller.dto.response.create;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String name;
    private String document;
    private AddressCreateResponse address;
    private ContactCreateResponse contact;
}
