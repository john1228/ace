package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author john
 * @date 19-6-5 下午6:00
 */
@Getter
@Setter
public class Wxpay {
    @JsonView(AdminView.Table.class)
    private String projectId;
    @JsonView(AdminView.Table.class)
    private String projectName;
    @JsonView(AdminView.Table.class)
    private String appId;
    @JsonView(AdminView.Table.class)
    private String mchId;
    private String secretKey;
    @JsonView(AdminView.Table.class)
    private Timestamp createdAt;
    @JsonView(AdminView.Table.class)
    private Timestamp updatedAt;
}
