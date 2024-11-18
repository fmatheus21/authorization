package com.fmatheus.app.infra.adapter.input.converter.impl;

import com.fmatheus.app.application.domain.AddressDomain;
import com.fmatheus.app.application.domain.ContactDomain;
import com.fmatheus.app.application.domain.PersonDomain;
import com.fmatheus.app.application.domain.UserDomain;
import com.fmatheus.app.infra.adapter.input.converter.UserUpdateConverter;
import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUpdateConverterImpl implements UserUpdateConverter {

    @Override
    public PersonDomain converterToUpdate(UserDomain user, UserUpdateDtoRequest request) {
        PersonDomain person = user.getPerson();
        person.setName(request.getName());
        AddressDomain address = converteAddress(user.getPerson().getAddress(), request.getAddress());
        ContactDomain contact = converterContact(user.getPerson().getContact(), request.getContact());

        person.setAddress(address);
        person.setContact(contact);

        return person;
    }

    private AddressDomain converteAddress(AddressDomain address, UserUpdateDtoRequest.AddressRequest request) {
        address.setCity(request.getCity());
        address.setNumber(request.getNumber());
        address.setState(request.getState());
        address.setComplement(request.getComplement());
        address.setDistrict(request.getDistrict());
        address.setPlace(request.getPlace());
        address.setZipCode(request.getZipCode());
        return address;
    }


    private ContactDomain converterContact(ContactDomain contact, UserUpdateDtoRequest.ContactRequest request) {
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        return contact;
    }


}
