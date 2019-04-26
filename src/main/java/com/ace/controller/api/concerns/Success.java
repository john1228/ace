package com.ace.controller.api.concerns;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by john on 17-10-9.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Success<T> extends Result {
    @JsonView(View.Base.class)
    private int status;
    @JsonView(View.Base.class)
    private T data;

    public Success(T data) {
        this.status = 1;
        this.data = data;
    }

}
