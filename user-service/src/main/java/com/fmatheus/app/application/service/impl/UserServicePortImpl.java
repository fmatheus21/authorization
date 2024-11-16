package com.fmatheus.app.application.service.impl;

import static com.fmatheus.app.application.format.DomainFormat.*;
import com.fmatheus.app.application.port.UserRepositoryPort;
import com.fmatheus.app.application.domain.UserDomain;
import com.fmatheus.app.application.service.UserServicePort;
import com.fmatheus.app.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
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
        var result = this.repositoryPort.findByUuid(uuid);
        return Optional.ofNullable(getUserDomainFormat(result.orElse(null)));
    }

    @Override
    public Optional<UserDomain> findByUsername(String username) {
        var result = this.repositoryPort.findByUsername(username);
        return Optional.of(getUserDomainFormat(Objects.requireNonNull(result.orElse(new UserDomain()))));
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
