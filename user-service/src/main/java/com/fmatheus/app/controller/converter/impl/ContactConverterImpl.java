package com.fmatheus.app.controller.converter.impl;

import com.fmatheus.app.controller.converter.ContactConverter;
import com.fmatheus.app.controller.dto.response.ContactResponse;
import com.fmatheus.app.controller.util.CharacterUtil;
import com.fmatheus.app.model.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ContactConverterImpl implements ContactConverter {

    private final ModelMapper mapper;

    @Override
    public Contact converterToEntity(Object o) {
        return null;
    }

    @Override
    public ContactResponse converterToResponse(Contact contact) {
        contact.setEmail(CharacterUtil.convertAllLowercaseCharacters(contact.getEmail()));
        return this.mapper.map(contact, ContactResponse.class);
    }
}
