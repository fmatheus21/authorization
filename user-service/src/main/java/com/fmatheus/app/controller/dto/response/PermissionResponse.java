package com.fmatheus.app.controller.dto.response;

import com.fmatheus.app.model.entity.Systems;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private String name;
    private Systems system;
}
