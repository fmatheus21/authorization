package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.infra.adapter.input.converter.UserUpdateConverter;
import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.input.util.CharacterUtil;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Address;
import com.fmatheus.app.infra.adapter.output.persistence.entity.Contact;
import com.fmatheus.app.infra.adapter.output.persistence.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUpdateConverterImpl implements UserUpdateConverter {

    @Override
    public User converterToUpdate(User user, UserUpdateDtoRequest request) {
        user.getPerson().setName(CharacterUtil.convertAllUppercaseCharacters(request.getName()));
        user.getPerson().setAddress(this.converteAddress(user.getPerson().getAddress(), request.getAddress()));
        user.getPerson().setContact(this.converterContact(user.getPerson().getContact(), request.getContact()));
        return user;
    }

    private Address converteAddress(Address address, UserUpdateDtoRequest.AddressRequest request) {
        address.setCity(CharacterUtil.convertAllUppercaseCharacters(request.getCity()));
        address.setNumber(CharacterUtil.convertAllUppercaseCharacters(request.getNumber()));
        address.setState(CharacterUtil.convertAllUppercaseCharacters(request.getState()));
        address.setComplement(CharacterUtil.convertAllUppercaseCharacters(request.getComplement()));
        address.setDistrict(CharacterUtil.convertAllUppercaseCharacters(request.getDistrict()));
        address.setPlace(CharacterUtil.convertAllUppercaseCharacters(request.getPlace()));
        address.setZipCode(CharacterUtil.removeSpecialCharacters(request.getZipCode()));
        return address;
    }


    private Contact converterContact(Contact contact, UserUpdateDtoRequest.ContactRequest request) {
        contact.setEmail(CharacterUtil.convertAllLowercaseCharacters(request.getEmail()));
        contact.setPhone(CharacterUtil.removeSpecialCharacters(request.getPhone()));
        return contact;
    }


}
