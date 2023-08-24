package com.fmatheus.app.controller.resource;


import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.UserPartialResponse;
import com.fmatheus.app.controller.dto.response.UserResponse;
import com.fmatheus.app.controller.rule.UserRule;
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
    public ResponseEntity<Page<UserPartialResponse>> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var response = this.rule.findAllFilter(pageable, filter);
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @ReadAuthorize
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(this.rule.findById(id));
    }

    @UpdateAuthorize
    @PutMapping
    public ResponseEntity<UserResponse> update(@RequestBody @Valid UserUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(this.rule.update(request, jwt));
    }

}
