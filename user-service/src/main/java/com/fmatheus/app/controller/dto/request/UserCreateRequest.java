package com.fmatheus.app.controller.dto.request;

import com.fmatheus.app.controller.dto.request.base.AddressCreateBase;
import com.fmatheus.app.controller.dto.request.base.ContactCreateBase;
import com.fmatheus.app.controller.dto.request.base.PermissionCreateBase;
import com.fmatheus.app.controller.dto.request.base.PersonTypeCreateBase;
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

    @Valid
    @NotNull
    private PersonTypeCreateBase personType;

    @Valid
    @NotNull
    private AddressCreateBase address;

    @Valid
    @NotNull
    private ContactCreateBase contact;

    @Valid
    @NotEmpty
    private Collection<PermissionCreateBase> permissions;

}
