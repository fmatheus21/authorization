package com.fmatheus.app.hexagonal.infra.adapter.input.security.authorize;


import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('user_service/all_authorize', 'user_service/user_create')")
public @interface UserCreateAuthorize {
}