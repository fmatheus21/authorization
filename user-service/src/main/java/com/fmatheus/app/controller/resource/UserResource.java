package com.fmatheus.app.controller.resource;


import com.fmatheus.app.controller.dto.request.create.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.update.PasswordUpdateRequest;
import com.fmatheus.app.controller.dto.request.update.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.controller.rule.UserRule;
import com.fmatheus.app.controller.security.authorize.CreateAuthorize;
import com.fmatheus.app.controller.security.authorize.ReadAuthorize;
import com.fmatheus.app.controller.security.authorize.UpdateAuthorize;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserRule rule;

    @ReadAuthorize
    @GetMapping
    public ResponseEntity<Page<PersonResponse>> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var response = this.rule.findAllFilter(pageable, filter);
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public PersonResponse findByUuid(@PathVariable UUID uuid) {
        return this.rule.findByUuid(uuid);
    }

    @UpdateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public PersonResponse update(@RequestBody @Valid UserUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.rule.update(request, jwt);
    }

    @UpdateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/password")
    public void updatePassword(@RequestBody @Valid PasswordUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
        this.rule.updatePassword(request, jwt);
    }

    @CreateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public PersonResponse create(@RequestBody @Valid UserCreateRequest request) {
        return this.rule.create(request);
    }

}
