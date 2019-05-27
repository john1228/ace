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
    private String column;
    private String order;

    public Sort(String column, String order) {
        this.column = column;
        this.order = order;
    }
}
