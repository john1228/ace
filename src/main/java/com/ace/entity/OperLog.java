package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;


/**
 * @author john
 * @date 19-6-13 上午11:54
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class OperLog {
    @NonNull
    @JsonView(AdminView.Table.class)
    private String empId;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String empName;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String controller;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String operation;
    @NonNull
    @JsonView(AdminView.Table.class)
    private String ip;
    @JsonView(AdminView.Table.class)
    private String createdAt;
}
