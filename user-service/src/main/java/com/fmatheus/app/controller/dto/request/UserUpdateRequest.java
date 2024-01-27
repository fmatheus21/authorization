package com.fmatheus.app.controller.dto.request;



import com.fmatheus.app.controller.dto.request.base.AddressUpdateBase;
import com.fmatheus.app.controller.dto.request.base.ContactUpdateBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotNull
    @NotBlank
    @Size(max = 70)
    private String name;

    @Valid
    private AddressUpdateBase address;

    @Valid
    private ContactUpdateBase contact;

}
