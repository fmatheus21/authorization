package com.fmatheus.app.controller.dto.response;

import com.fmatheus.app.controller.dto.BaseUuid;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPartialResponse extends BaseUuid {
    private String username;
    private boolean active;
    private PersonPartialResponse person;
    private ContactResponse contact;
}
