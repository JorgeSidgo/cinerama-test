package com.sidgo.cinerama.model.specification.user;

import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithUsername implements Specification<SctUser> {
    private String username;

    public UserWithUsername(String username) {
        super();
        this.username = username;
    }

    @Override
    public Predicate toPredicate(Root<SctUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (username == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("userName"), "%" + this.username + "%");
    }
}
