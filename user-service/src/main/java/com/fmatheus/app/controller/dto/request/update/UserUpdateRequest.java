package com.fmatheus.app.controller.dto.request.update;


import com.fmatheus.app.controller.dto.request.BaseRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest extends BaseRequest {

    @Valid
    private PersonUpdateRequest person;

}
