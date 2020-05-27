package com.sidgo.cinerama.model.specification.movie;

import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieWithAvailability implements Specification<CtgMovie> {
    private Integer availability;

    public MovieWithAvailability(Integer availability) {
        super();
        this.availability = availability;
    }

    @Override
    public Predicate toPredicate(Root<CtgMovie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (availability == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        Predicate state = criteriaBuilder.equal(root.get("state"), 1);
        Predicate availability = criteriaBuilder.equal(root.get("availability").get("id"),  this.availability);
        return criteriaBuilder.and(state, availability);
    }
}
