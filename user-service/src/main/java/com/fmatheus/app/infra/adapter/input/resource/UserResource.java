package com.fmatheus.app.infra.adapter.input.resource;


import com.fmatheus.app.infra.adapter.input.dto.request.PasswordUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserCreateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserPermissionUpdateRequest;
import com.fmatheus.app.infra.adapter.input.dto.request.UserUpdateDtoRequest;
import com.fmatheus.app.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.infra.adapter.input.exception.BadRequestException;
import com.fmatheus.app.infra.adapter.input.exception.ForbiddenException;
import com.fmatheus.app.infra.adapter.input.exception.ServerErrorException;
import com.fmatheus.app.infra.adapter.input.exception.UnauthorizedException;
import com.fmatheus.app.infra.adapter.input.exception.handler.MessageResponseHandler;
import com.fmatheus.app.infra.adapter.input.facade.UserFacade;
import com.fmatheus.app.infra.adapter.input.security.authorize.UserCreateAuthorize;
import com.fmatheus.app.infra.adapter.input.security.authorize.UserReadAuthorize;
import com.fmatheus.app.infra.adapter.input.security.authorize.UserUpdateAuthorize;
import com.fmatheus.app.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Tag(name = "Users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserFacade facade;

    @Operation(summary = "Consultar Registro Filtro", description = "Consultar regitro utilizando filtro", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDtoResponse.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @Transactional(readOnly = true)
    @UserReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ResponseEntity<Page<UserDtoResponse>> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var response = this.facade.findAllFilter(pageable, filter);
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Consultar Registro", description = "Consultar regitro por UUID", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @Transactional(readOnly = true)
    @UserReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public UserDtoResponse findByUuid(@Parameter(description = "Uuid of the user to search") @PathVariable UUID uuid) {
        return this.facade.findByUuid(uuid);
    }

    @Operation(summary = "Criar Registro", description = "Cria no registro de usuário", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @UserCreateAuthorize
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDtoResponse create(@RequestBody @Valid UserCreateDtoRequest request) {
        return this.facade.create(request);
    }

    @Operation(summary = "Atualiza Registro", description = "Atualiza registro do usuário", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @UserUpdateAuthorize
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping
    public UserDtoResponse update(@RequestBody @Valid UserUpdateDtoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.facade.update(request, jwt);
    }

    @Operation(summary = "Atualiza Senha", description = "Atualiza a senha do usuário", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MessageResponseHandler.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @UserUpdateAuthorize
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/password")
    public MessageResponseHandler updatePassword(@RequestBody @Valid PasswordUpdateDtoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.facade.updatePassword(request, jwt);
    }

    @Operation(summary = "Atualiza Permissões", description = "Inlui ou exclui permissões do usuário", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerErrorException.class)))
    })
    @UserCreateAuthorize
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/uuid/{uuid}/permissions")
    public void updatePermission(@PathVariable UUID uuid, @RequestBody @Valid UserPermissionUpdateRequest request) {
        this.facade.updatePermissions(uuid, request);
    }


}
