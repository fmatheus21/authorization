package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.response.base.AddressReadBase;
import com.fmatheus.app.controller.dto.response.base.ContactReadBase;
import com.fmatheus.app.controller.dto.response.base.PersonTypeReadBase;
import com.fmatheus.app.controller.dto.response.base.UserReadBase;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import lombok.*;

import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String document;
    private PersonTypeReadBase personType;
    private AddressReadBase address;
    private ContactReadBase contact;
    private Collection<UserReadBase> users;
    private MessageResponseHandler message;
}
