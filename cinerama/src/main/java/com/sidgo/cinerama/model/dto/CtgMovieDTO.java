package com.sidgo.cinerama.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sidgo.cinerama.model.entity.CtgMovie;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CtgMovieDTO {

    private long id;
    private String title;
    private String description;
    private long directorId;
    private String directorName;
    private long genreId;
    private String genreName;
    private int stock;
    private BigDecimal rentalPrice;
    private BigDecimal salePrice;
    private long availabilityId;
    private String availability;
    private Integer popularity;
    private boolean state;
    private List<CtgImageDTO> images;
    private Boolean likedByUser;

    public CtgMovieDTO() {
    }

    public CtgMovieDTO(CtgMovie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
        this.directorId = movie.getDirector().getId();
        this.directorName = movie.getDirector().getName();
        this.genreId = movie.getGenre().getId();
        this.genreName = movie.getGenre().getDescription();
        this.stock = movie.getStock();
        this.rentalPrice = movie.getRentalPrice();
        this.salePrice = movie.getSalePrice();
        this.availabilityId = movie.getAvailability().getId();
        this.availability = movie.getAvailability().getDescription();
        this.popularity = movie.getPopularity();
        this.state = movie.isState();
    }

    public CtgMovieDTO(long id, String title, Integer popularity, String availability) {
        this.id = id;
        this.title = title;
        this.popularity = popularity;
        this.availability = availability;
    }

    public CtgMovieDTO(long id, String title, String description, long directorId, String directorName, long genreId, String genreName, int stock, BigDecimal rentalPrice, BigDecimal salePrice, long availabilityId, String availability, Integer popularity, boolean state) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.directorId = directorId;
        this.directorName = directorName;
        this.genreId = genreId;
        this.genreName = genreName;
        this.stock = stock;
        this.rentalPrice = rentalPrice;
        this.salePrice = salePrice;
        this.availabilityId = availabilityId;
        this.availability = availability;
        this.popularity = popularity;
        this.state = state;
    }

    public static CtgMovieDTO fromEntity(CtgMovie entity) {
        return new CtgMovieDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getPopularity(),
                entity.getAvailability().getDescription()
        );
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

    public long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(long directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
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

    public long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public List<CtgImageDTO> getImages() {
        return images;
    }

    public void setImages(List<CtgImageDTO> images) {
        this.images = images;
    }

    public Boolean getLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(Boolean likedByUser) {
        this.likedByUser = likedByUser;
    }
}
