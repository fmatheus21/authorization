package com.fmatheus.app.config.properties;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LocationConfigProperties {
    private int connectTimeout;
    private int readTimeout;
}
