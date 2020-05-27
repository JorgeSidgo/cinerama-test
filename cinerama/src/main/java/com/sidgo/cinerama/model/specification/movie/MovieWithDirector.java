package com.sidgo.cinerama.model.specification.movie;

import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieWithDirector implements Specification<CtgMovie> {
    private String director;

    public MovieWithDirector(String director) {
        super();
        this.director = director;
    }

    @Override
    public Predicate toPredicate(Root<CtgMovie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (director == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("director").get("name"), "%" + this.director + "%");
    }
}
