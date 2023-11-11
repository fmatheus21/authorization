package com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "systems", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"id"})})
public class Systems extends Base {

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "system", fetch = FetchType.LAZY)
    private Collection<Permission> permissions;

}
