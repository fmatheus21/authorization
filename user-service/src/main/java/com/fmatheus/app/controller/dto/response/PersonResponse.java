package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private String name;
    private String document;
    private LocalDateTime createdAt;
    private AddressResponse address;
    private ContactResponse contact;
}
