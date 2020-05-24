package com.sidgo.cinerama.model.dto;

public class AuthenticationDTO {

    private long id;
    private String username;
    private String email;
    private String firstNames;
    private String lastNames;
    private String role;
    private String roleDisplay;
    private String token;
    private int state;

    public AuthenticationDTO() {
    }

    public AuthenticationDTO(long id, String username, String email, String firstNames, String lastNames, String role, String roleDisplay, String token, int state) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.role = role;
        this.roleDisplay = roleDisplay;
        this.token = token;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleDisplay() {
        return roleDisplay;
    }

    public void setRoleDisplay(String roleDisplay) {
        this.roleDisplay = roleDisplay;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
