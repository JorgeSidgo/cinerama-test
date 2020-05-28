package com.sidgo.cinerama.model.dto;

import java.util.Map;

public class MailDTO {

    public static final String PERSONAL = "Cinerama";
    public static final String NEW_USER = "User registered";
    public static final String FORGOT_PASSWORD = "Password recovery";

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> model;

    public MailDTO() {
    }

    public MailDTO(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

}
