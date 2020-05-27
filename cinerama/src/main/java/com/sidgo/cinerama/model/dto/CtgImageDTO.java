package com.sidgo.cinerama.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sidgo.cinerama.model.entity.CtgImage;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CtgImageDTO {
    private long id;
    private String code;
    private String path;
    private long imageTypeId;
    private String imageType;
    private long movieId;
    private String content;
    private String format;

    public CtgImageDTO() {
    }

    public CtgImageDTO(CtgImage image) {
        this.id = image.getId();
        this.code = image.getCode();
        this.path = image.getPath();
        this.imageTypeId = image.getImageType().getId();
        this.imageType = image.getImageType().getDescription();
        this.movieId = image.getMovie().getId();
    }

    public CtgImageDTO(long id, String code, String path, long imageTypeId, String imageType, long movieId) {
        this.id = id;
        this.code = code;
        this.path = path;
        this.imageTypeId = imageTypeId;
        this.imageType = imageType;
        this.movieId = movieId;
    }

    public CtgImageDTO(String path, long imageTypeId, String imageType) {
        this.path = path;
        this.imageTypeId = imageTypeId;
        this.imageType = imageType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getImageTypeId() {
        return imageTypeId;
    }

    public void setImageTypeId(long imageTypeId) {
        this.imageTypeId = imageTypeId;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}
