package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.dto.BaseUuid;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseUuid {
    private String username;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Collection<PermissionResponse> permissions;
}
