package com.ace.entity;

import com.ace.controller.api.concerns.ApiView;
import com.ace.entity.concern.Period;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;

/**
 * @author john
 * @date 19-5-7 下午5:05
 */
@Getter
@Setter
@NoArgsConstructor
public class Schedule {
    Logger logger = LoggerFactory.getLogger(Schedule.class);
    @JsonView(ApiView.Base.class)
    private Date date;
    @JsonView(ApiView.Base.class)
    private List<Period> open;
    @JsonView(ApiView.Base.class)
    private List<Period> appointed;

    public Schedule(Date date, List<Period> open, List<Period> appointed) {
        this.date = date;
        this.open = open;
        this.appointed = appointed;
    }
}
