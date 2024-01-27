package com.fmatheus.app.controller.dto.request;

import com.fmatheus.app.controller.dto.request.base.PermissionUpdateBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPermissionUpdateRequest {

    @Valid
    @NotNull
    private Collection<PermissionUpdateBase> permissions;

}
