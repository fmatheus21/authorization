package com.fmatheus.app.infra.adapter.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "user-service.openapi")
public class OpenApiProperties {

    private String description;
    private String title;
    private String version;
}
