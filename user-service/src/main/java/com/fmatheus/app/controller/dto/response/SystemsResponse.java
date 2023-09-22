package com.fmatheus.app.controller.dto.response;

import com.fmatheus.app.controller.dto.BaseUuid;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SystemsResponse extends BaseUuid {
    private String name;
}
