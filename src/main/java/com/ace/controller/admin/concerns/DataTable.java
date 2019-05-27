package com.ace.controller.admin.concerns;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by john on 17-5-13.
 */
@Getter
@Setter
public class DataTable<T> {
    @JsonView(AdminView.Table.class)
    private int start;
    @JsonView(AdminView.Table.class)
    private int length;
    @JsonView(AdminView.Table.class)
    private int draw;
    @JsonView(AdminView.Table.class)
    private Long recordsFiltered;
    @JsonView(AdminView.Table.class)
    private List<T> data;
}
