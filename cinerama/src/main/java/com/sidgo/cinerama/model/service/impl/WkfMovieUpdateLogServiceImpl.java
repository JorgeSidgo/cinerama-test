package com.sidgo.cinerama.model.service.impl;

import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.dto.SctUserDTO;
import com.sidgo.cinerama.model.entity.CtgMovie;
import com.sidgo.cinerama.model.entity.SctUser;
import com.sidgo.cinerama.model.entity.WkfMovieUpdateLog;
import com.sidgo.cinerama.model.repository.SctUserRepository;
import com.sidgo.cinerama.model.repository.WkfMovieUpdateLogRepository;
import com.sidgo.cinerama.model.service.WkfMovieUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WkfMovieUpdateLogServiceImpl implements WkfMovieUpdateLogService {

    @Autowired
    SctUserRepository sctUserRepository;

    @Autowired
    WkfMovieUpdateLogRepository wkfMovieUpdateLogRepository;

    private SctUser user;
    private CtgMovie movie;

    @Override
    public List<WkfMovieUpdateLog> save(CtgMovie current, CtgMovieDTO changes) {
        List<WkfMovieUpdateLog> list = new ArrayList<>();
        Object username = (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        SctUserDTO user = sctUserRepository.getUserByUsername(username.toString());
        this.movie = new CtgMovie(current.getId());
        this.user = new SctUser(user.getId());
        try {

            if (!current.getTitle().equals(changes.getTitle())) {
                list.add(resolveLog("Title", current.getTitle(), changes.getTitle()));
            }

            if (!current.getRentalPrice().equals(changes.getRentalPrice())) {
                list.add(resolveLog("Rental Price", String.valueOf(current.getRentalPrice()), String.valueOf(changes.getRentalPrice())));
            }

            if (!current.getRentalPrice().equals(changes.getRentalPrice())) {
                list.add(resolveLog("Sale Price", String.valueOf(current.getSalePrice()), String.valueOf(changes.getSalePrice())));
            }

            list = wkfMovieUpdateLogRepository.saveAll(list);

        } catch (Exception ex) {
            throw ex;
        }

        return list;
    }

    private WkfMovieUpdateLog resolveLog(String prop, String oldValue, String newValue) {
        return new WkfMovieUpdateLog(
                this.user,
                this.movie,
                prop,
                oldValue,
                newValue
        );
    }
}
