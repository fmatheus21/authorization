package com.fmatheus.app.controller.dto.response;


import com.fmatheus.app.model.entity.Base;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse extends Base {
    private String name;
    private SystemsResponse system;
}
