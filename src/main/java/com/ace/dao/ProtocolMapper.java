package com.ace.dao;

import com.ace.entity.Staff;
import org.apache.ibatis.annotations.Param;

/**
 * @author john
 * @date 19-6-6 上午9:32
 */
public interface ProtocolMapper {
    String findBy(@Param("orgId") String projectId);

    void update(@Param("staff") Staff staff, @Param("protocol") String protocol);
}
