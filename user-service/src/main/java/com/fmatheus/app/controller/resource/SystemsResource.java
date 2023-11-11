package com.fmatheus.app.controller.resource;

import com.fmatheus.app.controller.dto.response.SystemsResponse;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.controller.exception.ForbiddenException;
import com.fmatheus.app.controller.exception.UnauthorizedException;
import com.fmatheus.app.controller.rule.SystemsRule;
import com.fmatheus.app.controller.security.authorize.SystemsReadAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.UUID;


@Tag(name = "Systems")
@RequiredArgsConstructor
@RestController
@RequestMapping("/systems")
public class SystemsResource {

    private final SystemsRule rule;

    @Operation(summary = "Consult registration", description = "Consult registration by UUID", security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SystemsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = BadRequestException.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UnauthorizedException.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ForbiddenException.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServerError.class)))})
    @SystemsReadAuthorize
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{uuid}")
    public SystemsResponse findByUuid(@Parameter(description = "Uuid of the system to search") @PathVariable UUID uuid) {
        return this.rule.findByUuid(uuid);
    }


}
