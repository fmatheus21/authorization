package com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950233423L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @ToString.Exclude
    @JoinTable(name = "user_permission_join", joinColumns = {@JoinColumn(name = "id_permission", referencedColumnName = "id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users;

    @JoinColumn(name = "id_system", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Systems system;


}
