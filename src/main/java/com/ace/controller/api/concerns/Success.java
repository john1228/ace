package com.ace.controller.api.concerns;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Created by john on 17-10-9.
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Success<T> extends Result {
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private int status;
    @JsonView(ApiView.Base.class)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    @JsonView({ApiView.Base.class, ApiView.Detail.class, AdminView.Table.class})
    private T data;

    public Success(T data) {
        this.status = 1;
        this.data = data;
    }

}
