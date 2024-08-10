package com.fmatheus.app.hexagonal.infra.adapter.input.converter;


import com.fmatheus.app.hexagonal.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;

public interface UserUpdateConverter{

     User converterToUpdate(User user, UserUpdateDtoRequest request);
}
