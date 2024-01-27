package com.fmatheus.app.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class Profile extends Base {

    @Column(name = "name", nullable = false, length = 15)
    private String name;

}
