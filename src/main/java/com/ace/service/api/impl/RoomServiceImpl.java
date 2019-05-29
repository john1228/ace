package com.ace.service.api.impl;

import com.ace.controller.api.concerns.Query;
import com.ace.dao.PriceMapper;
import com.ace.dao.RoomClosedMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.*;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("api_room_service")
public class RoomServiceImpl extends BaseService implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Resource
    private RoomMapper rMapper;
    @Resource
    private PriceMapper pMapper;
    @Resource
    private RoomClosedMapper rcMapper;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;


    @Override
    public List<Room> query(Account account, Query query) {
        Date date = query.getDate();
        SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(df.format(query.getDate()).toUpperCase());
        List<Room> roomList = rMapper.query(account, query);
        List<Price> priceList = pMapper.priceList(roomList, date);
        List<RoomClosed> closedList = rcMapper.closedList(roomList, date);
        for (Room room : roomList) {
            priceList.stream().filter(item -> item.getWday().contains(week)).collect(Collectors.toSet()).forEach(item -> room.getOpen().add(new Period(item.getStartTime(), item.getEndTime())));
            List<Period> openList = new ArrayList<>();

            room.getOpen().forEach(period -> {
                Optional<RoomClosed> closed = closedList.stream().filter(item -> {
                    Timestamp pStart = Timestamp.valueOf(date.toString() + " " + period.getStartTime() + ":00");
                    Timestamp pEnd = Timestamp.valueOf(date.toString() + " " + period.getEndTime() + ":00");

                    Timestamp start = Timestamp.valueOf(date.toString() + " " + item.getStartTime() + ":00");
                    Timestamp end = Timestamp.valueOf(date.toString() + " " + item.getEndTime() + ":00");
                    return start.compareTo(pEnd) < 0 && end.compareTo(pStart) > 0;
                }).findFirst();
                if (closed.isPresent()) {
                    //处理特殊日期关闭
                    RoomClosed roomClosed = closed.get();
                    Timestamp pStart = Timestamp.valueOf(date.toString() + " " + period.getStartTime() + ":00");
                    Timestamp pEnd = Timestamp.valueOf(date.toString() + " " + period.getEndTime() + ":00");

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
            Set<Period> appointed = redisTemplate.opsForSet().members("ROOM::" + room.getId() + "::APPOINTED::" + date.toString());
            room.getAppointed().addAll(appointed);
        }
        return roomList;
    }

    @Override
    public Room show(Long id) {
        return rMapper.findById(id);
    }

    @Override
    public List<Schedule> schedule(Long room, Date date) {
        List<Schedule> schedules = new ArrayList<>();
        Long current = date.getTime();
        for (int i = 0; i < 7; i++) {
            Long timemills = current + i * 24 * 3600 * 1000;
            date.setTime(timemills);

            SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            Week week = Week.valueOf(df.format(date).toUpperCase());
            Set<Price> prices = pMapper.prices(room, date).stream().filter(item ->
                    item.getWday().contains(week)
            ).collect(Collectors.toSet());
            List<Period> opens = new ArrayList<>();
            for (Price price : prices) {
                opens.add(new Period(price.getStartTime(), price.getEndTime(), price.getPrice()));
            }
            Set<Period> appointed = redisTemplate.opsForSet().members("ROOM::" + room + "::APPOINTED::" + date.toString());

            Schedule schedule = new Schedule(new Date(timemills), opens, new ArrayList<>(appointed));
            schedules.add(schedule);
        }
        return schedules;
    }
}
