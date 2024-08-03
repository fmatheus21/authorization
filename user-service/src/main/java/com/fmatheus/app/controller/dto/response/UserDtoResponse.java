package com.fmatheus.app.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fmatheus.app.controller.enumerable.StatusSession;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
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
public class UserDtoResponse {
    private String name;
    private String document;
    private PersonTypeResponse personType;
    private AddressResponse address;
    private ContactResponse contact;
    private Collection<UserResponse> users;
    private MessageResponseHandler message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PersonTypeResponse {
        private UUID uuid;
        private String name;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressResponse {
        private String place;
        private String number;
        private String complement;
        private String district;
        private String city;
        private String state;
        private String zipCode;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContactResponse {
        private String email;
        private String phone;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        private UUID uuid;
        private String username;
        private boolean active;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private ProfileResponse profile;
        private Collection<PermissionResponse> permissions;
        private Collection<UserSessionsResponse> userSessions;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ProfileResponse {
            private String name;
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class PermissionResponse {
            private UUID uuid;
            private String name;
            private SystemsPermissionResponse system;

            @Builder
            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SystemsPermissionResponse {
                private UUID uuid;
                private String name;
                private String description;
            }
        }

        @Builder
        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class UserSessionsResponse {
            private String city;
            private String country;
            private String state;
            private LocalDateTime date;
            private StatusSession status;
            private String message;
            private SystemsUserSessionsResponse system;

            @Builder
            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SystemsUserSessionsResponse {
                private UUID uuid;
                private String name;
                private String description;
            }
        }

    }


}
