package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.converter.UserConverter;
import com.fmatheus.app.controller.converter.UserPartialConverter;
import com.fmatheus.app.controller.converter.UserUpdateConverter;
import com.fmatheus.app.controller.dto.request.PasswordRequest;
import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.UserPartialResponse;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.controller.exception.message.MessageResponse;
import com.fmatheus.app.model.entity.User;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import com.fmatheus.app.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserRule {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final UserConverter userConverter;

    private final UserPartialConverter userPartialConverter;

    private final UserUpdateConverter userUpdateConverter;

    private final MessageResponse messageResponse;

    public Page<UserPartialResponse> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var list = this.userService.findAllFilter(pageable, filter);
        var listConverter = list.map(this.userPartialConverter::converterToResponse).stream().toList();
        return new PageImpl<>(listConverter.stream().toList(), pageable, this.userService.totalPaginator(filter));
    }

    public UserResponse findById(UUID id) {
        var response = this.userService.findById(id).orElseThrow(this.messageResponse::errorRecordNotExist);
        return this.userConverter.converterToResponse(response);
    }

    public UserResponse update(UserUpdateRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        var commit = this.userService.save(this.userUpdateConverter.converterToUpdate(result, request));
        var converter = this.userConverter.converterToResponse(commit);
        converter.setMessage(this.messageResponse.messageSuccessUpdate());
        return converter;
    }


    public void updatePassword(PasswordRequest request, Jwt jwt) {
        var username = jwt.getClaims().get("username").toString();
        var result = this.findUser(username);
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            this.messageResponse.errorPasswordNotMatchException();
        }
        var password = this.passwordEncoder.encode(request.getPassword());
        result.setPassword(password);
        this.userService.save(result);
    }

    private User findUser(String username) {
        return this.userService.findByUsername(username).orElseThrow(this.messageResponse::errorUserdNotExist);
    }
}
