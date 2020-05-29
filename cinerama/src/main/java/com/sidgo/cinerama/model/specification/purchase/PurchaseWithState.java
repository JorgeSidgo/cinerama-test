package com.sidgo.cinerama.model.specification.purchase;

import com.sidgo.cinerama.model.entity.WkfPurchase;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PurchaseWithState implements Specification<WkfPurchase> {
    private long purchaseState;

    public PurchaseWithState(long purchaseState) {
        super();
        this.purchaseState = purchaseState;
    }

    @Override
    public Predicate toPredicate(Root<WkfPurchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (purchaseState == 0) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("purchaseState").get("id"), this.purchaseState);
    }
}
