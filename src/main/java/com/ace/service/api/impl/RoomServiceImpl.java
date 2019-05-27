package com.ace.service.api.impl;

import com.ace.controller.api.concerns.Query;
import com.ace.dao.PriceMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.Account;
import com.ace.entity.concern.Period;
import com.ace.entity.concern.enums.Week;
import com.ace.entity.Price;
import com.ace.entity.Room;
import com.ace.entity.Schedule;
import com.ace.service.api.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service("api_room_service")
public class RoomServiceImpl extends BaseService implements RoomService {
    Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private PriceMapper priceMapper;
    @Resource
    private RedisTemplate<String, Period> redisTemplate;


    @Override
    public List<Room> query(Account account, Query query) {
        Date date = query.getDate();
        SimpleDateFormat df = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(df.format(query.getDate()).toUpperCase());
        List<Room> roomList = roomMapper.query(account, query);
        List<Price> priceList = priceMapper.priceList(roomList, date);
        for (Room room : roomList) {
            Set<Period> appointed = redisTemplate.opsForSet().members("ROOM::" + room.getId() + "::APPOINTED::" + date.toString());
            room.getAppointed().addAll(appointed);
            Set<Price> prices = priceList.stream().filter(item ->
                    item.getWday().contains(week)
            ).collect(Collectors.toSet());
            List<Period> opens = new ArrayList<>();
            for (Price price : prices) {
                opens.add(new Period(price.getStartTime(), price.getEndTime()));
            }
            room.getOpen().addAll(opens);
        }

        return roomList;
    }

    @Override
    public Room show(Long id) {
        return roomMapper.findById(id);
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
            Set<Price> prices = priceMapper.prices(room, date).stream().filter(item ->
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
