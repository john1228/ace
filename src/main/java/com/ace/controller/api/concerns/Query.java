package com.ace.controller.api.concerns;

import com.ace.controller.admin.concerns.Sort;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * 会议室搜索条件
 */
@Setter
@Getter
public class Query {
    /**
     * 日期
     **/
    private Date date = new Date(System.currentTimeMillis());
    /**
     * 关键字
     **/
    private String keyword = "";
    /**
     * 容纳人数
     **/
    private Integer quota = 0;
    /**
     * sort - 排序 {@link Sort}
     */
    private Sort sort = new Sort("price", "asc");
    /**
     * 页码
     */
    private Integer page = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 20;
    private Integer start;

    public Integer getStart() {
        return (this.page - 1) * pageSize;
    }
}
