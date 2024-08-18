package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.data.query;

import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity.User;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryDataQuery {
    Page<User> findAllFilter(Pageable pageable, UserRepositoryFilter filter);

    Long total(UserRepositoryFilter filter);
}
