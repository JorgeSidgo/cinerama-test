package com.sidgo.cinerama.controller;

import com.sidgo.cinerama.model.service.CtgImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/v1/images")
public class ImageController {

    @Autowired
    CtgImageService ctgImageService;

    @GetMapping("/{image}")
    public ResponseEntity<byte[]> getImage(
            @PathVariable("image") String image
    ) throws FileNotFoundException {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(ctgImageService.retrieveImage(image));
    }
}
