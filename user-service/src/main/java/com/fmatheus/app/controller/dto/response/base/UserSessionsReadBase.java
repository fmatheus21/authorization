package com.fmatheus.app.controller.dto.response.base;

import com.fmatheus.app.controller.enumerable.StatusSession;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionsReadBase {
    private String ipAddress;
    private String city;
    private String country;
    private String state;
    private String latitude;
    private String longitude;
    private LocalDateTime date;
    private StatusSession status;
    private String message;
    private SystemsReadBase system;
}
