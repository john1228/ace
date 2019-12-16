package com.ace.controller.admin.concerns;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author john
 * @date 19-5-16 下午1:38
 */
@Setter
@Getter
@NoArgsConstructor
public class Sort {
    /**
     * 排序字段
     */
    private String column;
    /**
     * 排序方式(asc-升序 desc-降序)
     */
    private String order;

    public Sort(String column, String order) {
        this.column = column;
        this.order = order;
    }
}
