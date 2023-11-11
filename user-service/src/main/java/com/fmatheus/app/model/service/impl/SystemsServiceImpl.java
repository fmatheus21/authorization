package com.fmatheus.app.model.service.impl;

import com.fmatheus.app.model.entity.Systems;
import com.fmatheus.app.model.repository.SystemsRepository;
import com.fmatheus.app.model.service.SystemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class SystemsServiceImpl implements SystemsService {

    private final SystemsRepository repository;

    @Override
    public List<Systems> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Systems> findById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Systems save(Systems systems) {
        return this.repository.save(systems);
    }

    @Override
    public void deleteById(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Systems> findByUuid(UUID uuid) {
        return this.repository.findByUuid(uuid);
    }
}
