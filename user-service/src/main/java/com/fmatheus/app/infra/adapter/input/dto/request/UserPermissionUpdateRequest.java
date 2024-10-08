package com.fmatheus.app.infra.adapter.input.dto.request;

import com.fmatheus.app.infra.adapter.input.enumerable.MethodEnum;
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
    private Collection<PermissionRequest> permissions;


    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermissionRequest {

        @NotNull
        private Integer id;

        @NotNull
        private MethodEnum method;
    }

}
