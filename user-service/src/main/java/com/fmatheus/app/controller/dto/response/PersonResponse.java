package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.response.extension.AddressReadResponse;
import com.fmatheus.app.controller.dto.response.extension.ContactReadResponse;
import com.fmatheus.app.controller.dto.response.extension.PersonTypeReadResponse;
import com.fmatheus.app.controller.dto.response.extension.UserReadResponse;
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
    private PersonTypeReadResponse personType;
    private AddressReadResponse address;
    private ContactReadResponse contact;
    private UserReadResponse user;
    private MessageResponseHandler message;
}
