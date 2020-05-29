package com.sidgo.cinerama.model.dto;

public class PasswordUpdateDTO {
    private String newPassword;

    public PasswordUpdateDTO() {
    }

    public PasswordUpdateDTO(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
