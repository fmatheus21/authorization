package com.fmatheus.app.controller.security;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesAuthentication {
    private UUID uuid;
    private String name;
}
