package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRule {

    private final UserService userService;

    private final UserConverter converter;

    public Page<UserResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userService.findAllFilter(pageable, filter);
        var listConverter = list.map(this.converter::converterToResponse).stream().toList();
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userService.totalPaginator(filter));
    }

}
