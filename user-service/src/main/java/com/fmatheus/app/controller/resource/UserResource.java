package com.fmatheus.app.controller.resource;


import com.fmatheus.app.controller.dto.request.UserCreateRequest;
import com.fmatheus.app.controller.dto.request.extension.PasswordUpdateRequest;
import com.fmatheus.app.controller.dto.request.UserUpdateRequest;
import com.fmatheus.app.controller.dto.response.PersonResponse;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.controller.exception.ForbiddenException;
import com.fmatheus.app.controller.exception.UnauthorizedException;
import com.fmatheus.app.controller.exception.handler.MessageResponseHandler;
import com.fmatheus.app.controller.rule.UserRule;
import com.fmatheus.app.controller.security.authorize.CreateAuthorize;
import com.fmatheus.app.controller.security.authorize.ReadAuthorize;
import com.fmatheus.app.controller.security.authorize.UpdateAuthorize;
import com.fmatheus.app.model.repository.filter.UserRepositoryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.UUID;


@Tag(name = "Users")
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


    @Operation(summary = "Consult registration", description = "Consult registration by UUID", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerError.class)))})
    @ReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public PersonResponse findByUuid(@Parameter(description = "Uuid of the user to search") @PathVariable UUID uuid) {
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
    public MessageResponseHandler updatePassword(@RequestBody @Valid PasswordUpdateRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.rule.updatePassword(request, jwt);
    }

    @CreateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public PersonResponse create(@RequestBody @Valid UserCreateRequest request) {
        return this.rule.create(request);
    }


}
