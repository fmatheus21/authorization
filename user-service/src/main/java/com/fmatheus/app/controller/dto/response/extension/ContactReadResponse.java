package com.fmatheus.app.controller.dto.response.extension;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactReadResponse {
    private String email;
    private String phone;
}
