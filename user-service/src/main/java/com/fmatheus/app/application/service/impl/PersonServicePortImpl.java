package com.fmatheus.app.application.service.impl;

import com.fmatheus.app.application.domain.PersonDomain;

import static com.fmatheus.app.application.format.PersonDomainFormat.*;

import com.fmatheus.app.application.port.PersonRepositoryPort;
import com.fmatheus.app.application.service.PersonServicePort;


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
        var commit = this.repositoryPort.save(setPersonDomainFormat(personDomain));
        return getPersonDomainFormat(commit);
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
