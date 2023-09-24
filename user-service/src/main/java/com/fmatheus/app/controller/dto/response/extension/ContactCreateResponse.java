package com.fmatheus.app.controller.dto.response.extension;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactCreateResponse {
    private String email;
    private String phone;
}
