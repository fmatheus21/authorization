package com.fmatheus.app.controller.dto.request;

import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest {
    private UUID id;
}
