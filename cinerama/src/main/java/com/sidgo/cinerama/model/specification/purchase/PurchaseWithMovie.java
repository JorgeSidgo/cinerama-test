package com.sidgo.cinerama.model.specification.purchase;

import com.sidgo.cinerama.model.entity.WkfPurchase;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PurchaseWithMovie implements Specification<WkfPurchase> {
    private String movie;

    public PurchaseWithMovie(String movie) {
        super();
        this.movie = movie;
    }

    @Override
    public Predicate toPredicate(Root<WkfPurchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (movie == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("movie").get("title"), "%"+ this.movie +"%");
    }
}
