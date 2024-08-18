package com.fmatheus.app.hexagonal.infra.adapter.input.resource;


import com.fmatheus.app.hexagonal.infra.adapter.input.dto.response.UserDtoResponse;
import com.fmatheus.app.hexagonal.infra.adapter.input.facade.UserFacade;
import com.fmatheus.app.hexagonal.infra.adapter.input.security.authorize.UserReadAuthorize;
import com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter.UserRepositoryFilter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserFacade facade;

    @UserReadAuthorize
    @GetMapping
    public ResponseEntity<Page<UserDtoResponse>> findAllFilter(Pageable pageable, UserRepositoryFilter filter) {
        var response = this.facade.findAllFilter(pageable, filter);
        return !response.isEmpty() ? ResponseEntity.ok(response) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
/*

    @Operation(summary = "Consult registration", description = "Consult registration by UUID", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDtoResponse.class))), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))), @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))), @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))), @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerError.class)))})
    @UserReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public UserDtoResponse findByUuid(@Parameter(description = "Uuid of the user to search") @PathVariable UUID uuid) {
        return this.rule.findByUuid(uuid);
    }

    @UserUpdateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public UserDtoResponse update(@RequestBody @Valid UserUpdateDtoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.rule.update(request, jwt);
    }

    @UserUpdateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/password")
    public MessageResponseHandler updatePassword(@RequestBody @Valid PasswordUpdateDtoRequest request, @AuthenticationPrincipal Jwt jwt) {
        return this.rule.updatePassword(request, jwt);
    }

    @UserCreateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public UserDtoResponse create(@RequestBody @Valid UserCreateDtoRequest request) {
        return this.rule.create(request);
    }

    @UserCreateAuthorize
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/uuid/{uuid}/permissions")
    public void updatePermission(@PathVariable UUID uuid, @RequestBody @Valid UserPermissionUpdateRequest request) {
        this.rule.updatePermissions(uuid, request);
    }

*/
}
