package com.fmatheus.app.controller.dto.response;


import lombok.*;


@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocationResponse {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
