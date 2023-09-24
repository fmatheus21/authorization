package com.fmatheus.app.controller.dto.response.extension;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.BaseUuid;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionReadResponse extends BaseUuid {
    private String name;
    private SystemsReadResponse system;
}
