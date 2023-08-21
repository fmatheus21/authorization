package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private UUID id;
}
