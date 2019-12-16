package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


/**
 * 操作日志
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class OperLog {
    /**
     * 操作员编号
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String empId;
    /**
     * 操作员名字
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String empName;
    /**
     * 操作对象
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String controller;
    /**
     * 行为
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String operation;
    /**
     * 操作ip
     */
    @NonNull
    @JsonView(AdminView.Table.class)
    private String ip;
    /**
     * 操作日期
     */
    @JsonView(AdminView.Table.class)
    private String createdAt;
}
