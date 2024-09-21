package com.fmatheus.app.infra.adapter.output.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950200934L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 70)
    @Column(name = "name", nullable = false, length = 70)
    private String name;

    @NotNull
    @Size(max = 20)
    @Column(name = "document", nullable = false, length = 20)
    private String document;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Address address;

    @JoinColumn(name = "id_person_type", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private PersonType personType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.LAZY)
    private Collection<User> users;

}
