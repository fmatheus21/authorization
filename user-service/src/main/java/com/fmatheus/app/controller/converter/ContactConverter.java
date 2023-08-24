package com.fmatheus.app.controller.converter;


import com.fmatheus.app.controller.dto.response.ContactResponse;
import com.fmatheus.app.model.entity.Contact;

public interface ContactConverter extends MapperConverter<Contact, Object, ContactResponse> {
}
