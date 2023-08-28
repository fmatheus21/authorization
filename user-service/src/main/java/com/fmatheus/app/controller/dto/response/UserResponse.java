package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import lombok.*;

import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse {
    private String username;
    private boolean active;
    private PersonResponse person;
    private Collection<PermissionResponse> permissions;
    private MessageResponseHandler message;
}
