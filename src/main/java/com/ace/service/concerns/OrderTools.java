package com.ace.service.concerns;

import com.ace.entity.OrderSupport;
import com.ace.entity.Price;
import com.ace.entity.RoomSupport;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
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
public class OrderTools {
    public Optional<Price> fittedPrice(List<Price> prices, Timestamp start, Timestamp end, RoomRental rental) {
        Date date = new java.sql.Date(start.getTime());
        SimpleDateFormat wf = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Week week = Week.valueOf(wf.format(date).toUpperCase());
        return prices.stream().filter(item -> {
            if (item.getWday().contains(week)) {
                Timestamp itemStart = Timestamp.valueOf(date.toString() + " " + item.getStartTime() + ":00");
                Timestamp itemEnd = Timestamp.valueOf(date.toString() + " " + item.getEndTime() + ":00");
                switch (rental) {
                    case HOUR:
                        return itemStart.compareTo(start) <= 0 && itemEnd.compareTo(end) >= 0;
                    case PERIOD:
                        return itemStart.equals(start) && itemEnd.equals(end);
                    default:
                        return false;
                }
            } else {
                return false;
            }
        }).findFirst();
    }

    public BigDecimal cacl(List<OrderSupport> orderSupports, List<RoomSupport> roomSupports, StringBuilder errMsg) {
        BigDecimal total = new BigDecimal("0");
        orderSupports.forEach(orderSupport -> {
            Optional<RoomSupport> found = roomSupports.stream().filter(roomSupport -> roomSupport.getId() == orderSupport.getSupportId()).findFirst();
            if (found.isPresent()) {
                RoomSupport roomSupport = found.get();
                roomSupport.copyTo(orderSupport);
                total.add(roomSupport.getPrice().multiply(new BigDecimal(orderSupport.getAmount())));
            } else {
                errMsg.append("无效的服务");
            }
        });
        return total;
    }
}
