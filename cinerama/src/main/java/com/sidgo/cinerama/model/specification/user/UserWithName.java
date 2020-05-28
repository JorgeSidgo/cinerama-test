package com.sidgo.cinerama.model.specification.user;

import com.sidgo.cinerama.model.entity.SctUser;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserWithName implements Specification<SctUser> {

    private String name;

    public UserWithName(String name) {
        super();
        this.name = name;
    }

    @Override
    public Predicate toPredicate(Root<SctUser> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (name == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        Predicate firstNames = criteriaBuilder.like(root.get("firstNames"), "%" + this.name + "%");
        Predicate lastNames = criteriaBuilder.like(root.get("lastNames"), "%" + this.name + "%");
        return criteriaBuilder.or(firstNames, lastNames);
    }
}
