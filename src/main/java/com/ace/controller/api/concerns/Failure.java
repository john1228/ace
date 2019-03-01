package com.ace.controller.api.concerns;

/**
 * Created by john on 17-10-9.
 */
public class Failure extends Result {
    private int status;
    private String message;

    public Failure() {
        this.status = 0;
    }

    public Failure(String message) {
        this.status = 0;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
