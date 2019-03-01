package com.ace.controller.api.concerns;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by john on 17-10-9.
 */
public class Success extends Result {
    @JsonView(View.Base.class)
    private int status;
    @JsonView(View.Base.class)
    private Object data;

    public Success() {
        this.status = 1;
    }

    public Success(Object data) {
        this.status = 1;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
