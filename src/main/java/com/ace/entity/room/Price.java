package com.ace.entity.room;

import com.ace.entity.room.concern.RoomUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Price {
    private Long id;
    private String projectId;
    private String projectName;
    private String roomId;
    private String roomName;
    private RoomUtil.Rental rental;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
    private String startTime;
    private String endTime;
    private List<String> wday;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;

    public List<RoomUtil.Week> weeks() {
        List<RoomUtil.Week> wList = new ArrayList<>();
        wday.forEach(item -> wList.add(RoomUtil.Week.valueOf(item)));
        return wList;
    }
}
