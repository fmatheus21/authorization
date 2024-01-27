package com.fmatheus.app.controller.dto.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReadBase {
    private UUID uuid;
    private String username;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProfileReadBase profile;
    private Collection<PermissionReadBase> permissions;
    private Collection<UserSessionsReadBase> userSessions;
}
