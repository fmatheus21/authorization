package com.fmatheus.app.application.port;

import com.fmatheus.app.application.domain.ContactDomain;

import java.util.Optional;

public interface ContactRepositoryPort extends GenericPort<ContactDomain, Integer> {

    Optional<ContactDomain> findByEmail(String email);

    Optional<ContactDomain> findByPhone(String phone);
}
