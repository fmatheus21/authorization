package com.fmatheus.app.controller.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    private UUID id;


}
