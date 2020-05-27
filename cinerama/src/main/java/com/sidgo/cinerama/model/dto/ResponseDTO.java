package com.sidgo.cinerama.model.dto;

public class ResponseDTO {

    public static String COD_OK = "00";
    public static String MSG_OK = "Successful Request";

    public static String COD_ERR = "99";
    public static String MSG_ERR = "Incorrect Processing";

    public static String COD_AUTH_OK = "01";
    public static String MSG_AUTH_OK = "Authorized";

    public static String COD_AUTH_DND = "02";
    public static String MSG_AUTH_DND = "Bad Credentials";

    private String code;
    private int status;
    private String message;
    private Object errors;
    private Object result;

    public ResponseDTO() {
    }

    public ResponseDTO(String code, int status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ResponseDTO(String code, int status, String message, Object errors, Object result) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
