package com.sidgo.cinerama.model.service;

import com.sidgo.cinerama.model.dto.CtgImageDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface CtgImageService {

    List<CtgImageDTO> saveBulk(long idMovie, List<CtgImageDTO> list);
    CtgImageDTO save(long idMovie, CtgImageDTO imageDTO);
    CtgImageDTO updateImage(long idMovie, long idImage, CtgImageDTO image);
    byte[] retrieveImage(String imagePath) throws FileNotFoundException;
}
