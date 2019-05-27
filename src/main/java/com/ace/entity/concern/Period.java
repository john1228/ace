package com.ace.entity.concern;

import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.enums.RoomRental;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-5-5 下午7:15
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Period {
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String startTime;
    @JsonView({ApiView.Base.class, ApiView.Detail.class})
    private String endTime;
    @JsonView(ApiView.Detail.class)
    private RoomRental rental;
    @JsonView({ApiView.Base.class,ApiView.Detail.class})
    private BigDecimal price;

    public Period(String start, String end) {
        this.startTime = start;
        this.endTime = end;
    }

    public Period(String start, String end, BigDecimal price) {
        this.startTime = start;
        this.endTime = end;
        this.price = price;
    }
}
