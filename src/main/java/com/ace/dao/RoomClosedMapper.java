package com.ace.dao;

import com.ace.entity.RoomClosed;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author john
 * @date 19-5-20 上午11:05
 */
public interface RoomClosedMapper {
    List<RoomClosed> dataList(@Param("room") Long roomId, @Param("start") int start, @Param("length") int length);

    Long recordsTotal(@Param("room") Long roomId);

    void create(RoomClosed roomClosed);

    void delete(Long id);
}
