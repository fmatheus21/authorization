package com.fmatheus.app.config;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fmatheus.app.AuthorizationServerApplication;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@ComponentScan(basePackageClasses = AuthorizationServerApplication.class)
@Configuration
public class BeanConfig {


    @Bean
    public Decoder decoder() {
        return new JacksonDecoder(List.of(new JavaTimeModule()));
    }

    @Bean
    public Encoder encoder() {
        return new JacksonEncoder(List.of(new JavaTimeModule()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
