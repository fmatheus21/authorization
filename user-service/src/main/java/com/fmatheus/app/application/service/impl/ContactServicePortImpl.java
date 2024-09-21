package com.fmatheus.app.application.service.impl;

import com.fmatheus.app.application.domain.ContactDomain;
import com.fmatheus.app.application.port.ContactRepositoryPort;
import com.fmatheus.app.application.service.ContactServicePort;

import java.util.List;
import java.util.Optional;

public class ContactServicePortImpl implements ContactServicePort {

    private final ContactRepositoryPort repositoryPort;

    public ContactServicePortImpl(ContactRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<ContactDomain> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ContactDomain save(ContactDomain contactDomain) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ContactDomain> findById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ContactDomain> findByEmail(String email) {
        return this.repositoryPort.findByEmail(email);
    }

    @Override
    public Optional<ContactDomain> findByPhone(String phone) {
        return this.repositoryPort.findByPhone(phone);
    }

}
