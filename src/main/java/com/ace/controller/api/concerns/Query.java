package com.ace.controller.api.concerns;

import com.ace.controller.admin.concerns.Sort;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

/**
 * @author john
 * @date 19-5-16 下午1:50
 */
@Setter
@Getter
public class Query {
    private Date date = new Date(System.currentTimeMillis());
    private String keyword = "";
    private Integer quota = 0;
    private Sort sort = new Sort("price", "asc");
    private Integer page = 1;
    private Integer pageSize = 20;
    @ApiParam(hidden = true)
    private Integer start;

    public Integer getStart() {
        return (this.page - 1) * pageSize;
    }
}
