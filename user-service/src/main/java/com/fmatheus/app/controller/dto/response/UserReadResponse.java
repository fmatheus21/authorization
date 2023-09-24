package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.response.extension.AddressReadResponse;
import com.fmatheus.app.controller.dto.response.extension.ContactReadResponse;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReadResponse {
    private String name;
    private String document;
    private AddressReadResponse address;
    private ContactReadResponse contact;
    private com.fmatheus.app.controller.dto.response.extension.UserReadResponse user;
    private MessageResponseHandler message;
}
