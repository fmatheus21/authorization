package com.fmatheus.app.infra.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950244234L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

}
