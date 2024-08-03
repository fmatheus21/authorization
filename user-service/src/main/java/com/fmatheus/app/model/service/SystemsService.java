package com.fmatheus.app.model.service;

import com.fmatheus.app.model.entity.Systems;

import java.util.Optional;
import java.util.UUID;

public interface SystemsService extends GenericService<Systems, Integer> {

    Optional<Systems> findByUuid(UUID uuid);

}
