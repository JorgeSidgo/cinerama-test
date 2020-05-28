package com.sidgo.cinerama.model.specification.user;

import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserActive implements Specification<SctUser> {

    public UserActive() {
        super();
    }

    @Override
    public Predicate toPredicate(Root<SctUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate active = criteriaBuilder.equal(root.get("state"), 1);
        Predicate unconfirmed = criteriaBuilder.equal(root.get("state"), 2);
        return criteriaBuilder.or(active, unconfirmed);
    }
}
