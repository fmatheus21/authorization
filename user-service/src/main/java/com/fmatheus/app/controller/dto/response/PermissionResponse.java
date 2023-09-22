package com.fmatheus.app.controller.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.BaseUuid;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse extends BaseUuid {
    private String name;
    private SystemsResponse system;
}
