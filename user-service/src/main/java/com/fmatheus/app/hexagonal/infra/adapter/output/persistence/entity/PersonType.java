package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_type")
public class PersonType implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950233521L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @NotNull
    @Size(max = 15)
    @Column(name = "name", nullable = false, length = 15)
    private String name;


}
