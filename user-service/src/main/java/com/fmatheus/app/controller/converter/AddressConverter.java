package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.response.AddressResponse;
import com.fmatheus.app.model.entity.Address;

public interface AddressConverter extends MapperConverter<Address, Object, AddressResponse> {
}
