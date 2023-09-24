package com.fmatheus.app.controller.dto.response.extension;

import com.fmatheus.app.controller.dto.BaseUuid;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsReadResponse extends BaseUuid {
    private String name;
}
