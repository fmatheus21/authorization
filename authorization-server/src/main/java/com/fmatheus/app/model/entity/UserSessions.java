package com.fmatheus.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_sessions", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class UserSessions extends Base {

    @NotNull
    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @NotNull
    @Column(name = "device", nullable = false, length = 50)
    private String device;

    @NotNull
    @Column(name = "ip_address", nullable = false, length = 20)
    private String ipAddress;

    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private User user;
}
