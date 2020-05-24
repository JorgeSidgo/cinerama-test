package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ctg_profile_permission", schema = "cinerama")
public class SctProfilePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profile")
    private SctProfile profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_permission")
    private SctPermission permission;

    public SctProfilePermission() {
    }

    public SctProfilePermission(long id) {
        this.id = id;
    }

    public SctProfilePermission(long id, SctProfile profile, SctPermission permission) {
        this.id = id;
        this.profile = profile;
        this.permission = permission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SctProfile getProfile() {
        return profile;
    }

    public void setProfile(SctProfile profile) {
        this.profile = profile;
    }

    public SctPermission getPermission() {
        return permission;
    }

    public void setPermission(SctPermission permission) {
        this.permission = permission;
    }
}
