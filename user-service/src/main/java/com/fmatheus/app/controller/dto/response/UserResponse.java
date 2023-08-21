package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.util.Collection;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse {
    private String username;
    private boolean active;
    private PersonResponse person;
    private Collection<PermissionResponse> permissions;
}
