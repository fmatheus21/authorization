package com.fmatheus.app.hexagonal.infra.adapter.input.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsDtoResponse {
    private UUID uuid;
    private String name;
    private Collection<PermissionResponse> permissions;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PermissionResponse {
        private UUID uuid;
        private String name;
        private SystemsResponse system;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SystemsResponse {
        private UUID uuid;
        private String name;
        private String description;
    }

}
