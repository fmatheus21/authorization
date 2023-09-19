package com.fmatheus.app.controller.dto.response.create;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonCreateResponse {
    private String name;
    private String document;
    private AddressCreateResponse address;
    private ContactCreateResponse contact;
}
