package com.ace.controller.api.concerns;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * App请求成功返回结果
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Success<T> extends Result {
    /**
     * 返回码
     **/
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private int status;
    /**
     * 处理时间
     */
    @JsonView(ApiView.Base.class)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    /**
     * 返回数据
     */
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private T data;

    public Success(T data) {
        this.status = 1;
        this.data = data;
    }

}
