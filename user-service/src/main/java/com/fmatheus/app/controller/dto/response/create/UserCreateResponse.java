package com.fmatheus.app.controller.dto.response.create;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private PersonCreateResponse person;
}
