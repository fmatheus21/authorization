package com.fmatheus.app.hexagonal.application.domain;

import com.fmatheus.app.hexagonal.application.util.AppUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;


public class SystemsDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950255900L;

    private Integer id;
    private UUID uuid;
    private String name;
    private String description;
    private Collection<PermissionDomain> permissions;

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
        return AppUtil.convertAllLowercaseCharacters(name);
    }

    public void setName(String name) {
        this.name = AppUtil.convertAllUppercaseCharacters(name);
    }

    public String getDescription() {
        return AppUtil.convertFirstUppercaseCharacter(description);
    }

    public void setDescription(String description) {
        this.description = AppUtil.convertAllUppercaseCharacters(description);
    }

    public Collection<PermissionDomain> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<PermissionDomain> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemsDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
