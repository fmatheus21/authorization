package com.fmatheus.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_person"}),
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"username"})})
public class User extends Base {

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 70)
    private String password;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ToString.Exclude
    @NotNull
    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Person person;

    @ToString.Exclude
    @JoinTable(name = "user_permission_join", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_permission"))
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Permission> permissions;

    @ToString.Exclude
    @NotNull
    @JoinColumn(name = "id_profile", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Collection<UserSessions> userSessions;


}
