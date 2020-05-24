package com.sidgo.cinerama.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "ctg_movie", schema = "cinerama")
public class CtgMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_director")
    private CtgDirector director;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_genre")
    private CtgGenre genre;

    @Column(name = "stock")
    private int stock;

    @Column(name = "rental_price")
    private BigDecimal rentalPrice;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_availability")
    private CtgAvailability availability;

    @Column(name = "like_count")
    private BigInteger likeCount;

    @Column(name = "state")
    private boolean state;

    public CtgMovie() {
    }

    public CtgMovie(long id) {
        this.id = id;
    }

    public CtgMovie(long id, String title, String description, CtgDirector director, CtgGenre genre, int stock, BigDecimal rentalPrice, BigDecimal salePrice, CtgAvailability availability, BigInteger likeCount, boolean state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.director = director;
        this.genre = genre;
        this.stock = stock;
        this.rentalPrice = rentalPrice;
        this.salePrice = salePrice;
        this.availability = availability;
        this.likeCount = likeCount;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CtgDirector getDirector() {
        return director;
    }

    public void setDirector(CtgDirector director) {
        this.director = director;
    }

    public CtgGenre getGenre() {
        return genre;
    }

    public void setGenre(CtgGenre genre) {
        this.genre = genre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(BigDecimal rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public CtgAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(CtgAvailability availability) {
        this.availability = availability;
    }

    public BigInteger getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(BigInteger likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
