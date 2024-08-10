package com.fmatheus.app.model.repository;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.Systems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SystemsRepository extends JpaRepository<Systems, Integer> {
    Optional<Systems> findByUuid(UUID uuid);

}