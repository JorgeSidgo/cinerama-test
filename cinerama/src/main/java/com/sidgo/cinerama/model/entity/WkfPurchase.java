package com.sidgo.cinerama.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wkf_purchase", schema = "cinerama")
public class WkfPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private SctUser user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_movie")
    private CtgMovie movie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_purchase_type")
    private CtgPurchaseType purchaseType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_purchase_state")
    private CtgPurchaseState purchaseState;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "created_at")
    private Date createdAt;

    public WkfPurchase() {
    }

    public WkfPurchase(long id) {
        this.id = id;
    }

    public WkfPurchase(long id, SctUser user, CtgMovie movie, CtgPurchaseType purchaseType, CtgPurchaseState purchaseState, BigDecimal total, Date createdAt) {
        this.id = id;
        this.user = user;
        this.movie = movie;
        this.purchaseType = purchaseType;
        this.purchaseState = purchaseState;
        this.total = total;
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

    public CtgPurchaseType getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(CtgPurchaseType purchaseType) {
        this.purchaseType = purchaseType;
    }

    public CtgPurchaseState getPurchaseState() {
        return purchaseState;
    }

    public void setPurchaseState(CtgPurchaseState purchaseState) {
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
}

