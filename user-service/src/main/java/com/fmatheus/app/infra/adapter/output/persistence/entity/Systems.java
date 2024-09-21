package com.fmatheus.app.infra.adapter.output.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "systems")
public class Systems implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950255900L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "description", nullable = false, length = 150)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "system", fetch = FetchType.LAZY)
    private Collection<Permission> permissions;


}
