package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.dto.CtgImageDTO;
import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.dto.ResponseDTO;
import com.sidgo.cinerama.model.entity.CtgMovie;
import com.sidgo.cinerama.model.service.CtgImageService;
import com.sidgo.cinerama.model.service.CtgMovieService;
import com.sidgo.cinerama.model.specification.movie.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/movies")
public class MovieController {

    @Autowired
    CtgMovieService ctgMovieService;

    @Autowired
    CtgImageService ctgImageService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getActiveMovies(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "director", required = false) String director,
            @RequestParam(name = "genre", required = false) String genre
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {

            Specification<CtgMovie> spec = Specification
                    .where(new MovieActive())
                    .and(new MovieWithTitle(title))
                    .and(new MovieWithDirector(director))
                    .and(new MovieWithGenre(genre));

            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.getActiveMovies(spec, page, size, sort));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseDTO> getAdminMovies(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "director", required = false) String director,
            @RequestParam(name = "genre", required = false) String genre,
            @RequestParam(name = "availability", required = false) Integer availability
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {

            Specification<CtgMovie> spec = Specification
                    .where(new MovieWithTitle(title))
                    .and(new MovieWithDirector(director))
                    .and(new MovieWithGenre(genre))
                    .and(new MovieWithAvailability(availability));

            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.getActiveMovies(spec, page, size, sort));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getActiveMovieDetail(
            @PathVariable("id") long id
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.getActiveMovie(id));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> saveMovie(
            @RequestBody CtgMovieDTO movieDTO
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.save(movieDTO));
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateMovie(
            @PathVariable("id") long id,
            @RequestBody CtgMovieDTO movieDTO
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.update(id, movieDTO));
            status = HttpStatus.OK;
        } catch (Exception ex) {

            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteMovie(
            @PathVariable("id") long id
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.softDelete(id));
            status = HttpStatus.NO_CONTENT;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<ResponseDTO> saveMovieImage(
            @PathVariable("id") long id,
            @RequestBody CtgImageDTO image
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgImageService.save(id, image));
            status = HttpStatus.CREATED;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/{id}/images/{idImage}")
    public ResponseEntity<ResponseDTO> updateMovieImages(
            @PathVariable("id") long id,
            @PathVariable("idImage") long idImage,
            @RequestBody CtgImageDTO image
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgImageService.updateImage(id, idImage, image));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<ResponseDTO> likeMovie(
            @PathVariable("id") long id
    ) {
        ResponseDTO response = new ResponseDTO();
        HttpStatus status;

        try {
            response.setCode(ResponseDTO.COD_OK);
            response.setMessage(ResponseDTO.MSG_OK);
            response.setResult(ctgMovieService.likeMovie(id));
            status = HttpStatus.OK;
        } catch (Exception ex) {
            response.setCode(ResponseDTO.COD_ERR);
            response.setMessage(ResponseDTO.MSG_ERR);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        response.setStatus(status.value());
        return new ResponseEntity<>(response, status);
    }
}
