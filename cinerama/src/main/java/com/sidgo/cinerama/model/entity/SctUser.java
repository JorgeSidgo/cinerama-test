package com.sidgo.cinerama.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sct_user", schema = "cinerama")
public class SctUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "code")
    private String code;

    @Column(name = "username")
    private String userName;

    @Column(name = "firstnames")
    private String firstNames;

    @Column(name = "lastnames")
    private String lastNames;

    @Column(name = "email")
    private String email;

    @Column(name = "token")
    private String token;

    @JsonIgnore
    @Column(name = "pwd")
    private String pwd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile")
    private SctProfile profile;

    /*
     *   The field State from Entity SctUser varies depending on the following:
     *       1 -> Active User
     *       2 -> Unconfirmed User
     *       3 -> User waiting for password reset
     *       4 -> Inactive User (soft delete)
     */

    @Column(name = "state")
    private int state;

    @Column(name = "created_at")
    private Date createdAt;

    public SctUser() {
    }

    public SctUser(long id) {
        this.id = id;
    }

    public SctUser(long id, String code, String userName, String firstNames, String lastNames, String email, String token, String pwd, SctProfile profile, int state, Date createdAt) {
        this.id = id;
        this.code = code;
        this.userName = userName;
        this.firstNames = firstNames;
        this.lastNames = lastNames;
        this.email = email;
        this.token = token;
        this.pwd = pwd;
        this.profile = profile;
        this.state = state;
        this.createdAt = createdAt;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public SctProfile getProfile() {
        return profile;
    }

    public void setProfile(SctProfile profile) {
        this.profile = profile;
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
