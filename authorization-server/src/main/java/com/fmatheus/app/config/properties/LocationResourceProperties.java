package com.fmatheus.app.config.properties;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LocationResourceProperties {
    private String findCep;
}
