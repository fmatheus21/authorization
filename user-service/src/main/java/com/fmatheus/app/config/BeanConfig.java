package com.fmatheus.app.config;


import com.fmatheus.app.UserServiceApplication;
import com.fmatheus.app.hexagonal.application.port.UserRepositoryPort;
import com.fmatheus.app.hexagonal.application.service.impl.UserServicePortImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
@ComponentScan(basePackageClasses = UserServiceApplication.class)
public class BeanConfig {

    @Bean
    public UserServicePortImpl userServicePort(UserRepositoryPort repositoryPort) {
        return new UserServicePortImpl(repositoryPort);
    }

    @Bean
    public ModelMapper mapper() {
        var mapper = new ModelMapper();
        mapper
                .getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setUseOSGiClassLoaderBridging(true)
                .setPreferNestedProperties(false)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
        return mapper;
    }


    public Properties yamlProperties() {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(new ClassPathResource("i18n/messages.yml"));
        return bean.getObject();
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setCommonMessages(yamlProperties());
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
