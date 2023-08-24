package com.fmatheus.app.controller.dto.response;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {
    private String email;
    private String phone;
}
