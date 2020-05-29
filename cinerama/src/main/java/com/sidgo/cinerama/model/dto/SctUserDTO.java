package com.sidgo.cinerama.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sidgo.cinerama.model.entity.SctUser;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SctUserDTO {

    private long id;
    private String code;

    @NotNull(message = "userName may not be null")
    @NotEmpty(message = "userName may not be empty")
    private String userName;

    @NotNull(message = "firstNames may not be null")
    @NotEmpty(message = "firstNames may not be empty")
    private String firstNames;

    @NotNull(message = "lastNames may not be null")
    @NotEmpty(message = "lastNames may not be empty")
    private String lastNames;

    @NotNull(message = "email may not be null")
    @NotEmpty(message = "email may not be empty")
    private String email;
    private String profile;
    private long profileId;
    private String profileDisplay;
    private int state;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pwd;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tempPwd;
    private Date createdAt;


    public SctUserDTO() {

    }

    public SctUserDTO(long id, String userName, String firstNames, String lastNames, String email, long profileId, String profileDisplay, int state, Date createdAt) {
        this.id = id;
        this.userName = userName;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.profileId = profileId;
        this.profileDisplay = profileDisplay;
        this.state = state;
        this.createdAt = createdAt;
    }

    public SctUserDTO(long id, String code, String userName, String firstNames, String lastNames, String email, String profile, long profileId, String profileDisplay, String pwd, int state, Date createdAt) {
        this.id = id;
        this.code = code;
        this.userName = userName;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.profile = profile;
        this.profileId = profileId;
        this.profileDisplay = profileDisplay;
        this.pwd = pwd;
        this.state = state;
        this.createdAt = createdAt;
    }

    public static SctUserDTO fromEntity(SctUser entity) {
        return new SctUserDTO(
                entity.getId(),
                entity.getUserName(),
                entity.getFirstNames(),
                entity.getLastNames(),
                entity.getEmail(),
                entity.getProfile().getId(),
                entity.getProfile().getDisplayName(),
                entity.getState(),
                entity.getCreatedAt()
        );
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
