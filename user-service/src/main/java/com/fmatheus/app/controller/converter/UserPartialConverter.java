package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.response.UserPartialResponse;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.model.entity.User;

public interface UserPartialConverter extends MapperConverter<User, Object, UserResponse> {
}
