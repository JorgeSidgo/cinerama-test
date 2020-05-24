package com.sidgo.cinerama.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ctg_image", schema = "cinerama")
public class CtgImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "path")
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_image_type")
    private CtgImageType imageType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private CtgMovie movie;

    public CtgImage() {
    }

    public CtgImage(long id) {
        this.id = id;
    }

    public CtgImage(long id, String path, CtgImageType imageType, CtgMovie movie) {
        this.id = id;
        this.path = path;
        this.imageType = imageType;
        this.movie = movie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CtgImageType getImageType() {
        return imageType;
    }

    public void setImageType(CtgImageType imageType) {
        this.imageType = imageType;
    }

    public CtgMovie getMovie() {
        return movie;
    }

    public void setMovie(CtgMovie movie) {
        this.movie = movie;
    }
}
