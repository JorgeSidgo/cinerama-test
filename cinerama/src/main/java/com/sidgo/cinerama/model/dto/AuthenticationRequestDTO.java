package com.sidgo.cinerama.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthenticationRequestDTO {


    @NotNull(message = "username may not be null")
    @NotEmpty(message = "username may not be empty")
    private String username;

    @NotNull(message = "pwd may not be null")
    @NotEmpty(message = "pwd may not be empty")
    private String pwd;

    public AuthenticationRequestDTO() {
    }

    public AuthenticationRequestDTO(String username, String pwd) {
        this.username = username;
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
