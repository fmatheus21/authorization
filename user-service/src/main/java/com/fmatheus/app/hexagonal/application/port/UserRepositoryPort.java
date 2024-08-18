package com.fmatheus.app.hexagonal.application.port;

import com.fmatheus.app.hexagonal.application.domain.UserDomain;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort extends GenericPort<UserDomain, Integer> {
    Optional<UserDomain> findByUuid(UUID uuid);

    Optional<UserDomain> findByUsername(String username);

     Page<UserDomain> findAllFilter(Pageable pageable, UserRepositoryFilter filter);

    Long total(UserRepositoryFilter filter);
}
