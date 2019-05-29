package com.ace.service.concerns;

import com.ace.entity.OrderSupport;
import com.ace.entity.Price;
import com.ace.entity.RoomClosed;
import com.ace.entity.RoomSupport;
import com.ace.entity.concern.enums.RoomRental;
import com.ace.entity.concern.enums.Week;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author john
 * @date 19-5-29 上午11:16
 */
@Service("tools-room")
public class RoomTools {

    public boolean check(List<RoomClosed> closedList, Timestamp start, Timestamp end) {
        return closedList.stream().filter(closed -> {
            Timestamp cStart = Timestamp.valueOf(closed.getStartDate().toString() + " " + closed.getStartTime());
            Timestamp cEnd = Timestamp.valueOf(closed.getStartDate().toString() + " " + closed.getEndTime());
            return start.compareTo(cEnd) < 0 && end.compareTo(cStart) > 0;
        }).findFirst().isPresent();
    }
}
