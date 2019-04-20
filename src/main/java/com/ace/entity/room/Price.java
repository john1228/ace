package com.ace.entity.room;

import com.ace.entity.concern.Base;
import com.ace.entity.concern.EnumUtils;
import com.ace.entity.room.concern.RoomUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Price extends Base {
    private Long id;
    private List<String> roomId;
    private List<String> roomName;
    private RoomUtil.Rental rental;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private String startTime;
    private String endTime;
    private List<EnumUtils.Week> wday;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
}
