package com.ace.service.api.impl;

import com.ace.dao.MemberCouponMapper;
import com.ace.dao.PriceMapper;
import com.ace.dao.RoomMapper;
import com.ace.entity.*;
import com.ace.entity.concern.enums.Week;
import com.ace.service.api.CouponService;
import com.ace.service.concerns.OrderTools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("api-coupon-service")
public class CouponServiceImpl implements CouponService {

    @Resource
    MemberCouponMapper mcMapper;
    @Resource
    RoomMapper roomMapper;
    @Resource
    PriceMapper priceMapper;
    @Resource
    private OrderTools orderTools;

    @Override
    public List<MemberCoupon> couponList(Account account, Long roomId, Timestamp appointStartTime, Timestamp appointEndTime) {
        List<MemberCoupon> fittedCoupon;
        SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(wf.format(appointStartTime).toUpperCase());
        Room room = roomMapper.findById(roomId);
        Date appointedDate = new Date(appointStartTime.getTime());
        List<Price> prices = priceMapper.prices(roomId, appointedDate);
        Optional<Price> optional = orderTools.fittedPrice(prices, appointStartTime, appointEndTime, room.getRental());
        if (optional.isPresent()) {
            //查找优惠券
            List<MemberCoupon> couponList = mcMapper.projectCoupons(account, room.getProjectId());
            fittedCoupon = couponList.stream().filter(cp ->
                    (cp.getLimitWday().size() == 0 || cp.getLimitWday().contains(week)) && (cp.getLimitRoom().size() == 0 || cp.getLimitRoom().contains(roomId))
            ).collect(Collectors.toList());
        } else {
            fittedCoupon = new ArrayList<>();
        }
        return fittedCoupon;
    }
}
