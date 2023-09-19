package com.fmatheus.app.model.service;

import com.fmatheus.app.model.entity.Contact;

import java.util.Optional;
import java.util.UUID;

public interface ContactService extends GenericService<Contact, UUID> {
    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);
}
