package com.sidgo.cinerama.model.specification.purchase;

import com.sidgo.cinerama.model.entity.WkfPurchase;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PurchaseWithUsername implements Specification<WkfPurchase> {
    private String username;

    public PurchaseWithUsername(String username) {
        super();
        this.username = username;
    }

    @Override
    public Predicate toPredicate(Root<WkfPurchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (username == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("user").get("username"), "%"+ this.username +"%");
    }
}
