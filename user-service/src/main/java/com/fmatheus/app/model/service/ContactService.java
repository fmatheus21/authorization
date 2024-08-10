package com.fmatheus.app.model.service;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Contact;

import java.util.Optional;

public interface ContactService extends GenericService<Contact, Integer> {
    Optional<Contact> findByEmail(String email);

    Optional<Contact> findByPhone(String phone);
}
