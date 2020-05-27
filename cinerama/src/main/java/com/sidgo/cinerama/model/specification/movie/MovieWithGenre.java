package com.sidgo.cinerama.model.specification.movie;

import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class MovieWithGenre implements Specification<CtgMovie> {
    private String genre;

    public MovieWithGenre(String genre) {
        super();
        this.genre = genre;
    }

    @Override
    public Predicate toPredicate(Root<CtgMovie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (genre == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
        return criteriaBuilder.like(root.get("genre").get("description"), "%" + this.genre + "%");
    }
}
