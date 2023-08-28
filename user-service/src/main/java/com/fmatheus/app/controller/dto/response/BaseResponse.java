package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.util.UUID;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private UUID id;
}
