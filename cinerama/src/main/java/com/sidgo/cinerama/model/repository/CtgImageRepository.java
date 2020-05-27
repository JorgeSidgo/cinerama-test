package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.dto.CtgImageDTO;
import com.sidgo.cinerama.model.entity.CtgImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CtgImageRepository extends JpaRepository<CtgImage, Long>, PagingAndSortingRepository<CtgImage, Long> {

    @Query("select new com.sidgo.cinerama.model.dto.CtgImageDTO(i.path, i.imageType.id, i.imageType.description) from CtgImage i where i.movie.id = :id")
    List<CtgImageDTO> getCtgImageByMovieId(@Param("id") long id);

}
