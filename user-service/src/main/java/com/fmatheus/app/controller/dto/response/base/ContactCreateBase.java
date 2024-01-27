package com.fmatheus.app.controller.dto.response.base;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactCreateBase {
    private String email;
    private String phone;
}
