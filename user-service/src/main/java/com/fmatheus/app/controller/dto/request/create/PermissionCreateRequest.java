package com.fmatheus.app.controller.dto.request.create;

import com.fmatheus.app.controller.dto.request.BaseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionCreateRequest extends BaseRequest {

    @NotNull
    private String name;
}
