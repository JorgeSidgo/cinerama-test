package com.sidgo.cinerama.model.specification.movie;

import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieActive implements Specification<CtgMovie> {

    public MovieActive() {
        super();
    }


    @Override
    public Predicate toPredicate(Root<CtgMovie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate state = criteriaBuilder.equal(root.get("state"), 1);
        Predicate stock = criteriaBuilder.greaterThan(root.get("stock"), 0);
        Predicate availability = criteriaBuilder.equal(root.get("availability").get("id"),  1);
        return criteriaBuilder.and(state, stock, availability);
    }
}
