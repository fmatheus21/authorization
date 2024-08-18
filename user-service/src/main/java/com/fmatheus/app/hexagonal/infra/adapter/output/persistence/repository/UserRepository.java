package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository;

import com.fmatheus.app.hexagonal.application.domain.UserDomain;
import com.fmatheus.app.hexagonal.application.port.UserRepositoryPort;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.UserRepositoryData;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
@Primary
public class UserRepository implements UserRepositoryPort {

    private final UserRepositoryData data;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDomain> findAll() {
        var result = this.data.findAll();
        return result.stream().map(this::convertToDomain).toList();
    }

    @Override
    public UserDomain save(UserDomain domain) {
        var converter = this.data.save(this.converterToEntity(domain));
        return this.convertToDomain(converter);
    }

    @Override
    public Optional<UserDomain> findById(Integer id) {
        var resutl = this.data.findById(id);
        return resutl.map(this::convertToDomain);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Optional<UserDomain> findByUuid(UUID uuid) {
        var result = this.data.findByUuid(uuid);
        return result.map(this::convertToDomain);
    }

    @Override
    public Optional<UserDomain> findByUsername(String username) {
        var result = this.data.findByUsername(username);
        return result.map(this::convertToDomain);
    }

    @Override
    public Page<UserDomain> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var result = this.data.findAllFilter(pageable, filter);
        return result.map(this::convertToDomain);
    }

    @Override
    public Long total(UserRepositoryFilter filter) {
        return this.data.total(filter);
    }


    private UserDomain convertToDomain(User user) {
        return this.modelMapper.map(user, UserDomain.class);
    }

    private User converterToEntity(UserDomain domain) {
        return this.modelMapper.map(domain, User.class);
    }

}
