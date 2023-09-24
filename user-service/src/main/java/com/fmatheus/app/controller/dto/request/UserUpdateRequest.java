package com.fmatheus.app.controller.dto.request;



import com.fmatheus.app.controller.dto.request.extension.AddressUpdateRequest;
import com.fmatheus.app.controller.dto.request.extension.ContactUpdateRequest;
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
    private AddressUpdateRequest address;

    @Valid
    private ContactUpdateRequest contact;

}
