package com.fmatheus.app.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "authorization-server.feign.location")
public class FeignLocationProperties {
    private LocationConfigProperties config;
    private LocationResourceProperties resource;
}
