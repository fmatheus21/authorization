package com.fmatheus.app.hexagonal.application.service.impl;

import com.fmatheus.app.hexagonal.application.domain.UserDomain;
import com.fmatheus.app.hexagonal.application.port.UserRepositoryPort;
import com.fmatheus.app.hexagonal.application.service.UserServicePort;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserServicePortImpl implements UserServicePort {

    private final UserRepositoryPort repositoryPort;

    public UserServicePortImpl(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public List<UserDomain> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserDomain save(UserDomain personDomain) {
        return this.repositoryPort.save(personDomain);
    }

    @Override
    public Optional<UserDomain> findById(Integer id) {
        return this.repositoryPort.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<UserDomain> findByUuid(UUID uuid) {
        return this.repositoryPort.findByUuid(uuid);
    }

    @Override
    public Optional<UserDomain> findByUsername(String username) {
        return this.repositoryPort.findByUsername(username);
    }

    @Override
    public Page<UserDomain> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        return this.repositoryPort.findAllFilter(pageable, filter);
    }

    @Override
    public Long total(UserRepositoryFilter filter) {
        return this.repositoryPort.total(filter);
    }
}
