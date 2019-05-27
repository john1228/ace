package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.Base;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Price extends Base {
    private Long id;
    private List<Integer> roomId;
    @JsonView(AdminView.Table.class)
    private List<String> roomName;
    @JsonView(AdminView.Table.class)
    private RoomRental rental;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(AdminView.Table.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(AdminView.Table.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    @JsonView(AdminView.Table.class)
    private String startTime;
    @JsonView(AdminView.Table.class)
    private String endTime;
    @JsonView(AdminView.Table.class)
    private List<Week> wday;
    @JsonView(AdminView.Table.class)
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
}
