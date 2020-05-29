package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.CtgImageDTO;
import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.*;
import com.sidgo.cinerama.model.repository.CtgImageRepository;
import com.sidgo.cinerama.model.repository.CtgLikesRepository;
import com.sidgo.cinerama.model.repository.CtgMovieRepository;
import com.sidgo.cinerama.model.repository.SctUserRepository;
import com.sidgo.cinerama.model.service.CtgImageService;
import com.sidgo.cinerama.model.service.CtgMovieService;
import com.sidgo.cinerama.model.service.WkfMovieUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CtgMovieServiceImpl implements CtgMovieService {

    @Autowired
    CtgMovieRepository ctgMovieRepository;

    @Autowired
    CtgImageRepository ctgImageRepository;

    @Autowired
    CtgImageService ctgImageService;

    @Autowired
    WkfMovieUpdateLogService wkfMovieUpdateLogService;

    @Autowired
    CtgLikesRepository ctgLikesRepository;

    @Autowired
    SctUserRepository sctUserRepository;

    @Override
    public Page<CtgMovieDTO> getMovies(Specification<CtgMovie> spec, int page, int size, String sort) {
        Page<CtgMovieDTO> finalMovies = null;
        Page<CtgMovieDTO> moviesDTO;
        Page<CtgMovie> movies;
        Pageable pageable;

        pageable = getPageable(page, size, sort);
        movies = ctgMovieRepository.findAll(spec, pageable);
        moviesDTO = movies.map(CtgMovieDTO::fromEntity);
        finalMovies = moviesDTO.map(item -> {
            List<CtgImageDTO> images = ctgImageRepository.getCtgImageByMovieId(item.getId());
            item.setImages(images);
            return item;
        });

        return finalMovies;
    }

    @Override
    public CtgMovieDTO getActiveMovie(long id) {
        Object username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        SctUserDTO user = sctUserRepository.getUserByUsername(username.toString());

        CtgLikes like = ctgLikesRepository.findByUser_IdAndMovie_Id(user.getId(), id);
        List<CtgImageDTO> images = ctgImageRepository.getCtgImageByMovieId(id);
        CtgMovieDTO movie = ctgMovieRepository.getMovie(id);

        movie.setLikedByUser((like != null));

        movie.setImages(images);
        return movie;
    }

    @Override
    public CtgMovieDTO save(CtgMovieDTO movie) {
        CtgMovie ctgMovie = new CtgMovie();
        CtgMovieDTO ctgMovieDTO;
        List<CtgImageDTO> ctgImageDTOList;

        try {
            ctgMovie.setTitle(movie.getTitle());
            ctgMovie.setDescription(movie.getDescription());
            ctgMovie.setDirector(new CtgDirector(movie.getDirectorId()));
            ctgMovie.setGenre(new CtgGenre(movie.getGenreId()));
            ctgMovie.setStock(movie.getStock());
            ctgMovie.setRentalPrice(movie.getRentalPrice());
            ctgMovie.setSalePrice(movie.getSalePrice());
            ctgMovie.setAvailability(new CtgAvailability(movie.getAvailabilityId()));
            ctgMovie.setState(true);
            ctgMovie.setPopularity(0);

            ctgMovie = ctgMovieRepository.save(ctgMovie);
            ctgImageDTOList = ctgImageService.saveBulk(ctgMovie.getId(), movie.getImages());

            ctgMovieDTO = new CtgMovieDTO(ctgMovie);
            ctgMovieDTO.setImages(ctgImageDTOList);

        } catch (Exception ex) {
            throw ex;
        }

        return ctgMovieDTO;
    }

    @Override
    public CtgMovieDTO update(long id, CtgMovieDTO movie) {
        CtgMovie ctgMovie;
        CtgMovieDTO ctgMovieDTO;
        List<CtgImageDTO> ctgImageDTOList;
        List<WkfMovieUpdateLog> updateLogs;

        try {
            ctgMovie = ctgMovieRepository.getById(id);

            wkfMovieUpdateLogService.save(ctgMovie, movie);

            ctgMovie.setTitle(movie.getTitle());
            ctgMovie.setDescription(movie.getDescription());
            ctgMovie.setDirector(new CtgDirector(movie.getDirectorId()));
            ctgMovie.setGenre(new CtgGenre(movie.getGenreId()));
            ctgMovie.setStock(movie.getStock());
            ctgMovie.setRentalPrice(movie.getRentalPrice());
            ctgMovie.setSalePrice(movie.getSalePrice());
            ctgMovie.setAvailability(new CtgAvailability(movie.getAvailabilityId()));
            ctgMovie.setState(true);
            ctgMovie.setPopularity(0);


            ctgMovie = ctgMovieRepository.save(ctgMovie);
            ctgMovieDTO = new CtgMovieDTO(ctgMovie);

        } catch (Exception ex) {
            throw ex;
        }

        return ctgMovieDTO;
    }

    @Override
    public CtgMovieDTO toggleAvailability(long id) {
        CtgMovie movie = ctgMovieRepository.getOne(id);

        long availability = (movie.getAvailability().getId() == 1) ? 2 : 1;

        movie.setAvailability(new CtgAvailability(availability));

        return CtgMovieDTO.fromEntity(ctgMovieRepository.save(movie));
    }

    @Override
    public boolean likeMovie(long id) {
        Object username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        SctUserDTO user = sctUserRepository.getUserByUsername(username.toString());
        CtgLikes like = ctgLikesRepository.findByUser_IdAndMovie_Id(user.getId(), id);
        CtgMovie movie =  ctgMovieRepository.getOne(id);


        if(like == null) {
            like = ctgLikesRepository.save(new CtgLikes(
                    new CtgMovie(id),
                    new SctUser(user.getId())
            ));

            movie.setPopularity(movie.getPopularity() + 1);
            ctgMovieRepository.save(movie);
            return true;
        } else {
            ctgLikesRepository.delete(like);
            movie.setPopularity(movie.getPopularity() - 1);
            ctgMovieRepository.save(movie);
            return false;
        }

    }

    @Override
    public boolean softDelete(long id) {
        CtgMovie movie;
        try {
            movie = ctgMovieRepository.getOne(id);
            movie.setState(false);

            return true;

        } catch (Exception exception) {
            return false;
        }

    }

    private Pageable getPageable(int page, int size, String sort) {
        Sort sortObj;
        Direction direction;
        direction = (sort.startsWith("-")) ? Direction.DESC : Direction.ASC;

        sortObj = Sort.by(new Sort.Order(direction, sort.replace("-", "")));

        return PageRequest.of(page, size, sortObj);
    }

}
