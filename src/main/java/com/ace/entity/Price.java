package com.ace.entity;

import com.ace.controller.admin.concerns.AdminView;
import com.ace.entity.concern.Base;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 价格体系
 */
@Getter
@Setter
@NoArgsConstructor
public class Price extends Base {
    private Long id;
    @Size(min = 1, message = "会议室不能为空")
    private List<Integer> roomId = new ArrayList<>();
    @JsonView(AdminView.Table.class)
    private List<String> roomName;
    @JsonView(AdminView.Table.class)
    @NotNull(message = "出租方式不能为空")
    private RoomRental rental;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(AdminView.Table.class)
    @NotNull(message = "开始日期不能为空")
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonView(AdminView.Table.class)
    @NotNull(message = "结束日期不能为空")
    private Date endDate;
    @JsonView(AdminView.Table.class)
    @NotBlank(message = "开始时间不能为空")
    private String startTime = "08:00";
    @JsonView(AdminView.Table.class)
    @NotBlank(message = "结束时间不能为空")
    private String endTime = "20:00";
    @JsonView(AdminView.Table.class)
    @Size(min = 1, message = "适用周不能为空")
    private List<Week> wday = new ArrayList<>();
    @JsonView(AdminView.Table.class)
    @NotNull(message = "价格必须设置")
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;

    public Integer startH() {
        return string2Hour(startTime);
    }

    public Integer endH() {
        return string2Hour(endTime);
    }

    private Integer string2Hour(String source) {
        if (source == null) {
            return null;
        } else {
            String[] sr = source.split(":");
            Integer hours = Integer.valueOf(sr[0]) * 2;
            if (sr[0].equals("00")) {
                return hours;
            } else {
                return hours + 1;
            }
        }
    }
}
