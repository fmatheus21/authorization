package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.response.extension.AddressResponse;
import com.fmatheus.app.controller.dto.response.extension.ContactResponse;
import com.fmatheus.app.controller.dto.response.extension.PersonTypeResponse;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private String name;
    private String document;
    private PersonTypeResponse personType;
    private AddressResponse address;
    private ContactResponse contact;
    private UserResponse user;
    private MessageResponseHandler message;
}
