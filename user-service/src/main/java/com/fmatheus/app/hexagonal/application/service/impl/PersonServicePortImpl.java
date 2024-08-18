package com.fmatheus.app.hexagonal.application.service.impl;

import com.fmatheus.app.hexagonal.application.domain.PersonDomain;
import com.fmatheus.app.hexagonal.application.port.PersonRepositoryPort;
import com.fmatheus.app.hexagonal.application.service.PersonServicePort;


import java.util.List;
import java.util.Optional;

public class PersonServicePortImpl implements PersonServicePort {

    private final PersonRepositoryPort repositoryPort;

    public PersonServicePortImpl(PersonRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<PersonDomain> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public PersonDomain save(PersonDomain personDomain) {
        return this.repositoryPort.save(personDomain);
    }

    @Override
    public Optional<PersonDomain> findById(Integer id) {
        return this.repositoryPort.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }
}
