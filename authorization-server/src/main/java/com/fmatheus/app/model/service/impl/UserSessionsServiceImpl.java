package com.fmatheus.app.model.service.impl;

import com.fmatheus.app.model.entity.UserSessions;
import com.fmatheus.app.model.repository.UserSessionsRepository;
import com.fmatheus.app.model.service.UserSessionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSessionsServiceImpl implements UserSessionsService {

    private final UserSessionsRepository repository;

    @Override
    public List<UserSessions> findAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Optional<UserSessions> findById(Integer integer) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public UserSessions save(UserSessions userSessions) {
        return this.repository.save(userSessions);
    }

    @Override
    public void deleteById(Integer id) {
        this.repository.deleteById(id);
    }
}
