package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.entity.CtgMovie;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.io.IOException;
import java.net.URISyntaxException;

public interface CtgMovieService {
    Page<CtgMovieDTO> getActiveMovies(Specification<CtgMovie> spec, int page, int size, String sort);
    CtgMovieDTO getActiveMovie(long id);
    CtgMovieDTO save(CtgMovieDTO movie) throws IOException, URISyntaxException;
    CtgMovieDTO update(long id, CtgMovieDTO movie);
    boolean likeMovie(long id);
    boolean softDelete(long id);
}
