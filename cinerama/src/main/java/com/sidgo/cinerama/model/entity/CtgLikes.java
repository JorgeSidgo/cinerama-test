package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ctg_likes", schema = "cinerama")
public class CtgLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private CtgMovie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private SctUser user;

    public CtgLikes() {
    }

    public CtgLikes(long id) {
        this.id = id;
    }

    public CtgLikes(CtgMovie movie, SctUser user) {
        this.movie = movie;
        this.user = user;
    }

    public CtgLikes(long id, CtgMovie movie, SctUser user) {
        this.id = id;
        this.movie = movie;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CtgMovie getMovie() {
        return movie;
    }

    public void setMovie(CtgMovie movie) {
        this.movie = movie;
    }

    public SctUser getUser() {
        return user;
    }

    public void setUser(SctUser user) {
        this.user = user;
    }
}
