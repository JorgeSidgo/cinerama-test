package com.sidgo.cinerama.model.specification.user;

import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithEmail implements Specification<SctUser> {

    private String email;

    public UserWithEmail(String email) {
        super();
        this.email = email;
    }

    @Override
    public Predicate toPredicate(Root<SctUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (email == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("email"), "%" + this.email + "%");
    }
}
