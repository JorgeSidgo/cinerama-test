package com.sidgo.cinerama.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wfk_movie_update_log", schema = "cinerama")
public class WkfMovieUpdateLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private SctUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private CtgMovie movie;

    @Column(name = "updated_prop")
    private String updatedProp;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "created_at")
    private Date createdAt;

    public WkfMovieUpdateLog() {
    }

    public WkfMovieUpdateLog(long id) {
        this.id = id;
    }

    public WkfMovieUpdateLog(SctUser user, CtgMovie movie, String updatedProp, String oldValue, String newValue) {
        this.user = user;
        this.movie = movie;
        this.updatedProp = updatedProp;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.createdAt = new Date();
    }

    public WkfMovieUpdateLog(long id, SctUser user, CtgMovie movie, String updatedProp, String oldValue, String newValue, Date createdAt) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.updatedProp = updatedProp;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SctUser getUser() {
        return user;
    }

    public void setUser(SctUser user) {
        this.user = user;
    }

    public CtgMovie getMovie() {
        return movie;
    }

    public void setMovie(CtgMovie movie) {
        this.movie = movie;
    }

    public String getUpdatedProp() {
        return updatedProp;
    }

    public void setUpdatedProp(String updatedProp) {
        this.updatedProp = updatedProp;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
