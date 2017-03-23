package com.test.atm.model;

// Error Messages 
public class ServiceResponse {

    private int code = 200;
    private String message = "Success";
    private Object response = null;

    public ServiceResponse(int code, String message, Object response) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getResponse() {
        return response;
    }
}
