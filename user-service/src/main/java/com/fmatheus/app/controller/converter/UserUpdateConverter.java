package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.model.entity.User;

public interface UserUpdateConverter extends MapperConverter<User, UserUpdateRequest, Object> {

     User converterToUpdate(User user, UserUpdateRequest request);
}
