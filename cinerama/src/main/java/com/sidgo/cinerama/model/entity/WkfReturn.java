package com.sidgo.cinerama.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wkf_return", schema = "cinerama")
public class WkfReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_purchase")
    private WkfPurchase purchase;

    @Column(name = "estimated_return_date")
    private Date estimatedReturnDate;

    public WkfReturn() {
    }

    public WkfReturn(long id) {
        this.id = id;
    }

    public WkfReturn(long id, WkfPurchase purchase, Date estimatedReturnDate) {
        this.id = id;
        this.purchase = purchase;
        this.estimatedReturnDate = estimatedReturnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WkfPurchase getPurchase() {
        return purchase;
    }

    public void setPurchase(WkfPurchase purchase) {
        this.purchase = purchase;
    }

    public Date getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(Date estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }
}
