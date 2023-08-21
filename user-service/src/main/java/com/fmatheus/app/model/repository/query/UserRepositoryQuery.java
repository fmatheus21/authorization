package com.fmatheus.app.model.repository.query;

import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryQuery {
    Page<User> findAllFilter(Pageable pageable, UserRepositoryFilter filter);

    Long total(UserRepositoryFilter filter);
}
