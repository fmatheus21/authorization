package com.fmatheus.app.application.domain;


import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;


public class PermissionDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950233423L;

    private Integer id;
    private UUID uuid;
    private String name;
    private Collection<UserDomain> users;
    private SystemsDomain system;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<UserDomain> getUsers() {
        return users;
    }

    public void setUsers(Collection<UserDomain> users) {
        this.users = users;
    }

    public SystemsDomain getSystem() {
        return system;
    }

    public void setSystem(SystemsDomain system) {
        this.system = system;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


}
