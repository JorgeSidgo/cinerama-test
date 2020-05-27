package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.CtgMovieDTO;
import com.sidgo.cinerama.model.entity.CtgMovie;
import com.sidgo.cinerama.model.entity.WkfMovieUpdateLog;

import java.util.List;

public interface WkfMovieUpdateLogService {

    public List<WkfMovieUpdateLog> save(CtgMovie current, CtgMovieDTO changes);

}
