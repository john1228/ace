package com.ace.service.concerns;

import com.ace.entity.OrderSupport;
import com.ace.entity.Price;
import com.ace.entity.RoomSupport;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author john
 * @date 19-5-29 上午11:16
 */
@Service("tools-order")
@Slf4j
public class OrderTools {
    /**
     * 计算预约时间段价格
     *
     * @param prices
     * @param start
     * @param end
     * @param rental
     * @return
     */
    public BigDecimal fittedPrice(List<Price> prices, Timestamp start, Timestamp end, RoomRental rental) throws Exception {
        Date date = new java.sql.Date(start.getTime());
        SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(wf.format(date).toUpperCase());

        switch (rental) {
            case HOUR:
                //按时间段预约切分半小时计算价格
                BigDecimal total = new BigDecimal(0);
                int i = 0;
                while (end.after(new Timestamp(start.getTime() + i * 30 * 60 * 1000))) {
                    boolean isFitted = false;
                    for (Price price : prices) {
                        if (price.getWday().contains(week)) {
                            Timestamp _start = Timestamp.valueOf(date.toString() + " " + price.getStartTime() + ":00");
                            Timestamp _end = Timestamp.valueOf(date.toString() + " " + price.getEndTime() + ":00");

                            Timestamp hfStart = new Timestamp(start.getTime() + i * 30 * 60 * 1000);
                            Timestamp hfEnd = new Timestamp(start.getTime() + (i + 1) * 30 * 60 * 1000);
                            if (_start.compareTo(hfStart) <= 0 && _end.compareTo(hfEnd) >= 0) {
                                total = total.add(price.getPrice());
                                break;
                            }
                        }
                    }
                    if (isFitted) throw new Exception("该时间段未开放预约");
                    i++;
                }
                return total;
            case PERIOD:
                Optional<Price> fittedPrice = prices.stream().filter(item -> {
                    if (item.getWday().contains(week)) {
                        Timestamp _start = Timestamp.valueOf(date.toString() + " " + item.getStartTime() + ":00");
                        Timestamp _end = Timestamp.valueOf(date.toString() + " " + item.getEndTime() + ":00");
                        return _start.equals(start) && _end.equals(end);
                    } else {
                        return false;
                    }
                }).findFirst();
                if (fittedPrice.isPresent()) {
                    return fittedPrice.get().getPrice();
                } else {
                    throw new Exception("该时间段未开放预约");
                }
            default:
                throw new Exception("该时间段未开放预约");
        }
    }

    public BigDecimal cacl(List<OrderSupport> orderSupports, List<RoomSupport> roomSupports, StringBuilder errMsg) {
        BigDecimal total = new BigDecimal("0");
        for (OrderSupport orderSupport : orderSupports) {
            Optional<RoomSupport> found = roomSupports.stream().filter(roomSupport -> roomSupport.getId() == orderSupport.getSupportId()).findFirst();
            if (found.isPresent()) {
                RoomSupport roomSupport = found.get();
                roomSupport.copyTo(orderSupport);
                total = total.add(roomSupport.getPrice().multiply(new BigDecimal(orderSupport.getAmount())));
            } else {
                errMsg.append("无效的服务");
            }
        }
        return total;
    }
}
