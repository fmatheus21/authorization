package com.fmatheus.app.model.service.impl;


import com.fmatheus.app.hexagonal.infra.adapter.input.util.CharacterUtil;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.model.repository.UserRepository;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public List<User> findAll() {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<User> findById(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<User> findByUuid(UUID uuid) {
        return this.repository.findByUuid(uuid);
    }


    @Override
    public User save(User user) {
        return this.repository.save(user);
    }


    @Override
    public void deleteById(Integer id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(CharacterUtil.removeSpecialCharacters(username));
    }


    @Override
    public Page<User> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        return this.repository.findAllFilter(pageable, filter);
    }

    @Override
    public Long totalPaginator(UserRepositoryFilter filter) {
        return this.repository.total(filter);
    }


}
