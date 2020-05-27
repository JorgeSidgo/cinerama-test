package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CtgMovieRepository extends JpaRepository<CtgMovie, Long>, PagingAndSortingRepository<CtgMovie, Long>, JpaSpecificationExecutor<CtgMovie> {

    @Query(
            "select new com.sidgo.cinerama.model.dto.CtgMovieDTO(" +
                    "m.id, " +
                    "m.title, " +
                    "m.description, " +
                    "m.director.id, " +
                    "m.director.name, " +
                    "m.genre.id, " +
                    "m.genre.description, " +
                    "m.stock," +
                    "m.rentalPrice, " +
                    "m.salePrice, " +
                    "m.availability.id, " +
                    "m.availability.description, " +
                    "m.popularity, " +
                    "m.state " +
                    ") " +
                    "from CtgMovie m where m.id = :id and m.state = true and m.availability.id = 1 and m.stock > 0"
    )
    CtgMovieDTO getMovie(@Param("id") long id);

    @Query("select m from CtgMovie m where m.id = :id")
    CtgMovie getById(@Param("id") long id);
}
