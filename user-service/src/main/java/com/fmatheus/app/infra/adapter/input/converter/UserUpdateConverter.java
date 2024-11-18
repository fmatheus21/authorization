package com.fmatheus.app.infra.adapter.input.converter;


import com.fmatheus.app.application.domain.PersonDomain;
import com.fmatheus.app.application.domain.UserDomain;
import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;

public interface UserUpdateConverter{

     PersonDomain converterToUpdate(UserDomain user, UserUpdateDtoRequest request);
}
