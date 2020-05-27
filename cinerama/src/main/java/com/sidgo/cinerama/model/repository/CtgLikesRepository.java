package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.entity.CtgLikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CtgLikesRepository extends JpaRepository<CtgLikes, Long> {

    CtgLikes findByUser_IdAndMovie_Id(long user_id, long movie_id);

}
