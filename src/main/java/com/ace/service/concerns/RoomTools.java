package com.ace.service.concerns;

import com.ace.entity.RoomClosed;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author john
 * @date 19-5-29 上午11:16
 */
@Service("tools-room")
public class RoomTools {

    public boolean check(List<RoomClosed> closedList, Timestamp start, Timestamp end) {
        return closedList.stream().filter(closed -> {
            Timestamp cStart = Timestamp.valueOf(closed.getStartDate().toString() + " " + closed.getStartTime() + ":00");
            Timestamp cEnd = Timestamp.valueOf(closed.getStartDate().toString() + " " + closed.getEndTime() + ":00");
            return start.compareTo(cEnd) < 0 && end.compareTo(cStart) > 0;
        }).findFirst().isPresent();
    }
}
