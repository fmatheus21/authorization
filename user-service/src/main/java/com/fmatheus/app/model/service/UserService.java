package com.fmatheus.app.model.service;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User, Integer> {

    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUsername(String username);

    public Page<User> findAllFilter(Pageable pageable, UserRepositoryFilter filter);

    Long totalPaginator(UserRepositoryFilter filter);

}
