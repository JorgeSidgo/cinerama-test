package com.sidgo.cinerama.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class SctUserDTO {

    private long id;
    private String userName;
    private String firstNames;
    private String lastNames;
    private String email;
    private String profile;
    private long profileId;
    private String profileDisplay;
    private int state;
    @JsonIgnore
    private String pwd;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tempPwd;


    public SctUserDTO() {

    }

    public SctUserDTO(long id, String userName, String firstNames, String lastNames, String email, String profile, long profileId, String profileDisplay, int state) {
        this.id = id;
        this.userName = userName;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.profile = profile;
        this.profileId = profileId;
        this.profileDisplay = profileDisplay;
        this.state = state;
    }

    public SctUserDTO(long id, String userName, String firstNames, String lastNames, String email, String profile, long profileId, String profileDisplay, String pwd, int state) {
        this.id = id;
        this.userName = userName;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.profile = profile;
        this.profileId = profileId;
        this.profileDisplay = profileDisplay;
        this.pwd = pwd;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getProfileDisplay() {
        return profileDisplay;
    }

    public void setProfileDisplay(String profileDisplay) {
        this.profileDisplay = profileDisplay;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getTempPwd() {
        return tempPwd;
    }

    public void setTempPwd(String tempPwd) {
        this.tempPwd = tempPwd;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


}
