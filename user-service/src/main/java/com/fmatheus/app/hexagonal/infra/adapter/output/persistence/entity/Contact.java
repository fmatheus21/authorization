package com.fmatheus.app.hexagonal.infra.adapter.output.persistence.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950283345L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Size(max = 80)
    @Email
    @Column(name = "email", nullable = false, length = 80)
    private String email;

    @NotNull
    @Size(max = 15)
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @JoinColumn(name = "id_person", referencedColumnName = "id", nullable = false)
    @OneToOne(optional = false)
    private Person person;

}
