package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.AddressConverter;
import com.fmatheus.app.controller.dto.response.AddressResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AddressConverterImpl implements AddressConverter {

    private final ModelMapper mapper;

    @Override
    public Address converterToEntity(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public AddressResponse converterToResponse(Address address) {
        address.setPlace(CharacterUtil.convertFirstUppercaseCharacter(address.getPlace()));
        address.setComplement(CharacterUtil.convertFirstUppercaseCharacter(address.getComplement()));
        address.setDistrict(CharacterUtil.convertFirstUppercaseCharacter(address.getDistrict()));
        address.setCity(CharacterUtil.convertFirstUppercaseCharacter(address.getCity()));
        address.setState(CharacterUtil.convertAllUppercaseCharacters(address.getState()));
        return this.mapper.map(address, AddressResponse.class);
    }
}
