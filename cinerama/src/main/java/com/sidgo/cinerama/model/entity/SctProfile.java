package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "sct_profile", schema = "cinerama")
public class SctProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    public SctProfile() {
    }

    public SctProfile(long id) {
        this.id = id;
    }

    public SctProfile(long id, String name, String displayName) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
