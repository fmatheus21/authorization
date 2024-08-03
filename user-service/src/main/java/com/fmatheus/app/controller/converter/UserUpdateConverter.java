package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.model.entity.User;

public interface UserUpdateConverter{

     User converterToUpdate(User user, UserUpdateDtoRequest request);
}
