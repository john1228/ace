package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author john
 * @date 19-6-5 下午6:00
 */
@Getter
@Setter
public class Alipay {
    @JsonView(AdminView.Table.class)
    @NotBlank(message = "项目不能为空")
    private String projectId;
    @JsonView(AdminView.Table.class)
    private String projectName;
    @JsonView(AdminView.Table.class)
    @NotBlank(message = "商户号不能为空")
    private String seller;
    @NotBlank(message = "私钥不能为空")
    private String privateKey;
    @NotBlank(message = "秘钥不能为空")
    private String publicKey;
    @JsonView(AdminView.Table.class)
    private Timestamp createdAt;
    @JsonView(AdminView.Table.class)
    private Timestamp updatedAt;
}
