package com.fmatheus.app.application.service;

import com.fmatheus.app.application.domain.UserDomain;
import com.fmatheus.app.application.port.GenericPort;
import com.fmatheus.app.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserServicePort extends GenericPort<UserDomain, Integer> {
    Optional<UserDomain> findByUuid(UUID uuid);

    Optional<UserDomain> findByUsername(String username);

    Page<UserDomain> findAllFilter(Pageable pageable, UserRepositoryFilter filter);

    Long total(UserRepositoryFilter filter);
}
