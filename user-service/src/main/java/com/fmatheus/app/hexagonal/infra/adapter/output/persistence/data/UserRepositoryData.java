package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.query.UserRepositoryDataQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepositoryData extends JpaRepository<User, Integer>, UserRepositoryDataQuery {
    Optional<User> findByUuid(UUID uuid);

    Optional<User> findByUsername(String username);
}
