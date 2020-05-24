package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ctg_availability", schema = "cinerama")
public class CtgAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    public CtgAvailability() {
    }

    public CtgAvailability(long id) {
        this.id = id;
    }

    public CtgAvailability(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
