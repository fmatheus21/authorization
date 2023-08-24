package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonPartialResponse {
    private String name;
    private LocalDateTime createdAt;
}
