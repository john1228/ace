package com.ace.controller.api.concerns;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by john on 17-10-9.
 */
@Getter
@Setter
public class Failure extends Result {
    @JsonView(ApiView.Base.class)
    private int status = 0;
    @JsonView(ApiView.Base.class)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @JsonView(ApiView.Base.class)
    private String message;

    public Failure(String message) {
        this.message = message;
    }
}
