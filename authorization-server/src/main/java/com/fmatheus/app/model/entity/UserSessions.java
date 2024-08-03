package com.fmatheus.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fmatheus.app.controller.enumerable.StatusSession;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_sessions")
public class UserSessions implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950290045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "zip_code", nullable = false, length = 20)
    private String zipCode;

    @NotNull
    @Column(name = "place", nullable = false, length = 50)
    private String place;

    @NotNull
    @Column(name = "district", nullable = false, length = 30)
    private String district;

    @NotNull
    @Column(name = "city", nullable = false, length = 30)
    private String city;

    @NotNull
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", nullable = false, length = 15)
    private StatusSession status;

    @NotNull
    @Column(name = "message", nullable = false, length = 100)
    private String message;

    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;

    @JoinColumn(name = "id_systems", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Systems system;
}
