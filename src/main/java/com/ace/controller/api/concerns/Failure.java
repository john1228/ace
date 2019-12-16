package com.ace.controller.api.concerns;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * App请求失败返回结果
 */
@Getter
@Setter
public class Failure extends Result {
    /**
     * 错误码
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private int status = 0;
    /**
     * 发生时间
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    /**
     * 错误内容
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String message;

    public Failure(String message) {
        this.message = message;
    }
}
