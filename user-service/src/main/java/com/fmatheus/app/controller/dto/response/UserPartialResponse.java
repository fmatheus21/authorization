package com.fmatheus.app.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPartialResponse extends BaseResponse {
    private String username;
    private boolean active;
    private PersonPartialResponse person;
    private ContactResponse contact;
}