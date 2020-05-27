package com.sidgo.cinerama.model.repository;

import com.sidgo.cinerama.model.entity.WkfMovieUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface WkfMovieUpdateLogRepository extends JpaRepository<WkfMovieUpdateLog, Long>, PagingAndSortingRepository<WkfMovieUpdateLog, Long> {
}
