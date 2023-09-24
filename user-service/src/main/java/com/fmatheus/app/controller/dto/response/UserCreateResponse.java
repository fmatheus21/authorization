package com.fmatheus.app.controller.dto.response;


import com.fmatheus.app.controller.dto.response.extension.AddressCreateResponse;
import com.fmatheus.app.controller.dto.response.extension.ContactCreateResponse;
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
