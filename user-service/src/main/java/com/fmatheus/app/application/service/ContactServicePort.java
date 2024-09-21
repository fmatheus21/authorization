package com.fmatheus.app.application.service;

import com.fmatheus.app.application.domain.ContactDomain;
import com.fmatheus.app.application.port.GenericPort;

import java.util.Optional;

public interface ContactServicePort extends GenericPort<ContactDomain, Integer> {

    Optional<ContactDomain> findByEmail(String email);

    Optional<ContactDomain> findByPhone(String phone);

}
