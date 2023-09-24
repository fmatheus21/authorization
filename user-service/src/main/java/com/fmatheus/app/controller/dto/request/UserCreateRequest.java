package com.fmatheus.app.controller.dto.request;

import com.fmatheus.app.controller.dto.request.extension.AddressCreateRequest;
import com.fmatheus.app.controller.dto.request.extension.ContactCreateRequest;
import com.fmatheus.app.controller.dto.request.extension.PermissionCreateRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    @Size(max = 70)
    private String name;

    @NotNull
    @Size(max = 20)
    private String document;

    private Integer personTypeId;

    @Valid
    @NotNull
    private AddressCreateRequest address;

    @Valid
    @NotNull
    private ContactCreateRequest contact;

    @Valid
    @NotEmpty
    private Collection<PermissionCreateRequest> permissions;

}
