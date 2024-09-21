package com.fmatheus.app.infra.adapter.input.converter;


import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.output.persistence.entity.User;

public interface UserUpdateConverter{

     User converterToUpdate(User user, UserUpdateDtoRequest request);
}
