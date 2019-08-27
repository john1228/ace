package com.ace.service.api.impl;

import com.ace.controller.api.concerns.Query;
import com.ace.dao.*;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.RoomService;
import com.ace.util.Aliyun;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("api_room_service")
@Log4j2
public class RoomServiceImpl extends BaseService implements RoomService {
    @Resource
    private RoomMapper rMapper;
    @Resource
    private PriceMapper pMapper;
    @Resource
    private RoomClosedMapper rcMapper;
    @Resource
    private ProtocolMapper protocolMapper;
    @Resource
    private OrderMapper orderMapper;


    @Override
    public List<Room> query(Account account, Query query) {
        Date date = query.getDate();
        Long timemills = date.getTime();
        SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(df.format(query.getDate()).toUpperCase());
        List<Room> roomList = rMapper.query(account, query);
        if (roomList.size() == 0) return new ArrayList<>();
        List<Price> priceList = pMapper.priceList(roomList, date);
        List<RoomClosed> closedList = rcMapper.closedList(roomList, date);
        for (Room room : roomList) {
            priceList.stream().filter(item -> item.getWday().contains(week)).collect(Collectors.toSet()).forEach(item -> room.getOpen().add(new Period(item.getStartTime(), item.getEndTime())));
            List<Period> openList = new ArrayList<>();

            room.getOpen().forEach(period -> {
                Timestamp pStart = Timestamp.valueOf(date.toString() + " " + period.getStartTime() + ":00");
                Timestamp pEnd = Timestamp.valueOf(date.toString() + " " + period.getEndTime() + ":00");
                Optional<RoomClosed> closed = closedList.stream().filter(item -> {
                    Timestamp start = Timestamp.valueOf(date.toString() + " " + item.getStartTime() + ":00");
                    Timestamp end = Timestamp.valueOf(date.toString() + " " + item.getEndTime() + ":00");
                    return start.compareTo(pEnd) < 0 && end.compareTo(pStart) > 0;
                }).findFirst();
                if (closed.isPresent()) {
                    //处理特殊日期关闭
                    RoomClosed roomClosed = closed.get();
                    Timestamp cStart = Timestamp.valueOf(date.toString() + " " + roomClosed.getStartTime() + ":00");
                    Timestamp cEnd = Timestamp.valueOf(date.toString() + " " + roomClosed.getEndTime() + ":00");
                    if (cEnd.compareTo(pStart) < 0 || cStart.compareTo(pEnd) > 0) {
                        openList.add(period);
                    } else {
                        if (cStart.compareTo(pStart) > 0) {
                            if (cEnd.compareTo(pEnd) > 0) {
                                openList.add(new Period(period.getStartTime(), roomClosed.getStartTime()));
                            } else {
                                openList.add(new Period(period.getStartTime(), roomClosed.getStartTime()));
                                openList.add(new Period(roomClosed.getEndTime(), period.getEndTime()));
                            }
                        } else {
                            if (cEnd.compareTo(pEnd) < 0) {
                                openList.add(new Period(roomClosed.getEndTime(), period.getEndTime()));
                            }
                        }
                    }

                } else {
                    openList.add(period);
                }
            });
            List<Period> appointedList = orderMapper.appointedList(room.getId(), new Date(timemills), new Date(timemills + 24 * 3600 * 1000));
            room.getAppointed().addAll(appointedList);
        }
        return roomList;
    }

    @Override
    public Room show(Long id) {
        Room room = rMapper.findById(id);
        List<String> imgList = new ArrayList<>();
        room.getImage().forEach(path -> imgList.add(Aliyun.Instance.imgHost + path));
        room.setImage(imgList);
        return room;
    }

    @Override
    public List<Schedule> schedule(Long room, Date date) {
        List<Schedule> schedules = new ArrayList<>();
        Long current = date.getTime();
        Date from = new Date(System.currentTimeMillis());
        Date to = new Date(System.currentTimeMillis());
        for (int i = 0; i < 7; i++) {
            Long timemills = current + i * 24 * 3600 * 1000;
            date.setTime(timemills);
            from.setTime(timemills);
            to.setTime(timemills + 24 * 3600 * 1000);

            SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            Week week = Week.valueOf(df.format(date).toUpperCase());
            Set<Price> prices = pMapper.prices(room, date).stream().filter(item ->
                    item.getWday().contains(week)
            ).collect(Collectors.toSet());
            List<Period> opens = prices.stream().map(price -> new Period(price.getStartTime(), price.getEndTime(), price.getPrice())).collect(Collectors.toList());
            List<Period> appointedList = orderMapper.appointedList(room, from, to);
            Schedule schedule = new Schedule(new Date(timemills), opens, appointedList);
            schedules.add(schedule);
        }
        return schedules;
    }


    @Override
    public String protocol(Long id) {
        Room room = rMapper.findById(id);
        return protocolMapper.findBy(room.getOrgId());
    }
}
