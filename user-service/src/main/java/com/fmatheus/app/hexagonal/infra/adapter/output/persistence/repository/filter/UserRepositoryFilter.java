package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.repository.filter;


import lombok.*;

/**
 * Classe que contem os atributos de um filtro que sao utilizados em consultas.
 *
 * @author Fernando Matheus
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRepositoryFilter {
    private String name;
    private String document;
    private String email;
    private String username;
}
