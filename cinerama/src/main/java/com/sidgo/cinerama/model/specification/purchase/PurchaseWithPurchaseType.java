package com.sidgo.cinerama.model.specification.purchase;

import com.sidgo.cinerama.model.entity.WkfPurchase;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PurchaseWithPurchaseType implements Specification<WkfPurchase> {

    private long purchaseType;

    public PurchaseWithPurchaseType(long purchaseType) {
        super();
        this.purchaseType = purchaseType;
    }

    @Override
    public Predicate toPredicate(Root<WkfPurchase> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (purchaseType == 0) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.equal(root.get("purchaseType").get("id"), this.purchaseType);
    }
}
