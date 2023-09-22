package com.fmatheus.app.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person_type", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "name"}),
})
public class PersonType extends Base {

    @NotNull
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @NotNull
    @Size(max = 15)
    @Column(name = "name", nullable = false, length = 15)
    private String name;


}
