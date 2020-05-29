package com.sidgo.cinerama.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sidgo.cinerama.model.entity.WkfPurchase;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class WkfPurchaseDTO {

    private long id;
    private long idUser;
    private String userName;
    private long idMovie;
    private String movieTitle;
    private long purchaseTypeId;
    private String purchaseType;
    private long purchaseStateId;
    private String purchaseState;
    private BigDecimal total;
    private Date createdAt;
    private Date estimatedReturn;

    public WkfPurchaseDTO() {
    }

    public WkfPurchaseDTO(long id) {
        this.id = id;
    }

    public WkfPurchaseDTO(long id, long idUser, String userName, long idMovie, String movieTitle, long purchaseTypeId, String purchaseType, long purchaseStateId, String purchaseState, BigDecimal total, Date createdAt, Date estimatedReturn) {
        this.id = id;
        this.idUser = idUser;
        this.userName = userName;
        this.idMovie = idMovie;
        this.movieTitle = movieTitle;
        this.purchaseTypeId = purchaseTypeId;
        this.purchaseType = purchaseType;
        this.purchaseStateId = purchaseStateId;
        this.purchaseState = purchaseState;
        this.total = total;
        this.createdAt = createdAt;
        this.estimatedReturn = estimatedReturn;
    }

    public static WkfPurchaseDTO fromEntity(WkfPurchase entity) {
        return new WkfPurchaseDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getUser().getUserName(),
                entity.getMovie().getId(),
                entity.getMovie().getTitle(),
                entity.getPurchaseType().getId(),
                entity.getPurchaseType().getDescription(),
                entity.getPurchaseState().getId(),
                entity.getPurchaseState().getDescription(),
                entity.getTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN),
                entity.getCreatedAt(),
                entity.getEstimatedReturn()
        );
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(long idMovie) {
        this.idMovie = idMovie;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public long getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(long purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public long getPurchaseStateId() {
        return purchaseStateId;
    }

    public void setPurchaseStateId(long purchaseStateId) {
        this.purchaseStateId = purchaseStateId;
    }

    public String getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(String purchaseState) {
        this.purchaseState = purchaseState;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getEstimatedReturn() {
        return estimatedReturn;
    }

    public void setEstimatedReturn(Date estimatedReturn) {
        this.estimatedReturn = estimatedReturn;
    }
}
