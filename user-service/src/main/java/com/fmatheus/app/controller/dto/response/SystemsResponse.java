package com.fmatheus.app.controller.dto.response;

import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsResponse extends BaseResponse {
    private String name;
}
