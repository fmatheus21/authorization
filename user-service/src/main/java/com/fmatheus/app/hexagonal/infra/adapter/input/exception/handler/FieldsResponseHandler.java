package com.fmatheus.app.hexagonal.infra.adapter.input.exception.handler;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldsResponseHandler {

    private String field;
    private String message;
}