package com.fmatheus.app.application.domain;

import com.fmatheus.app.application.util.AppUtil;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;


public class UserDomain implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950255399L;

    private Integer id;
    private UUID uuid;
    private String username;
    private String password;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private PersonDomain person;
    private Collection<PermissionDomain> permissions;
    private ProfileDomain profile;
    private Collection<UserSessionsDomain> userSessions;

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

    public String getUsername() {
        return AppUtil.convertAllLowercaseCharacters(username);
    }

    public void setUsername(String username) {
        this.username = AppUtil.convertAllUppercaseCharacters(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PersonDomain getPerson() {
        return person;
    }

    public void setPerson(PersonDomain person) {
        this.person = person;
    }

    public Collection<PermissionDomain> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<PermissionDomain> permissions) {
        this.permissions = permissions;
    }

    public ProfileDomain getProfile() {
        return profile;
    }

    public void setProfile(ProfileDomain profile) {
        this.profile = profile;
    }

    public Collection<UserSessionsDomain> getUserSessions() {
        return userSessions;
    }

    public void setUserSessions(Collection<UserSessionsDomain> userSessions) {
        this.userSessions = userSessions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDomain that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

}
