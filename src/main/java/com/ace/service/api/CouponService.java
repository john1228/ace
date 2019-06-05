package com.ace.service.api;

import com.ace.entity.*;

import java.sql.Timestamp;
import java.util.List;

public interface CouponService {
    List<MemberCoupon> couponList(Account account, Long roomId, Timestamp appointStartTime, Timestamp appointEndTime);
}
