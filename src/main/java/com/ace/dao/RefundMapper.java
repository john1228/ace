package com.ace.dao;

import com.ace.controller.admin.concerns.RefundCriteria;
import com.ace.entity.Order;
import com.ace.entity.RefundApplication;
import com.ace.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author john
 * @date 19-7-29 下午6:09
 */
public interface RefundMapper {

    Long recordsTotal(@Param("staff") Staff staff, @Param("criteria") RefundCriteria criteria);

    List<RefundApplication> dataList(@Param("staff") Staff staff, @Param("criteria") RefundCriteria criteria, @Param("start") int start, @Param("length") int length);

    void create(RefundApplication application);

    void agree(@Param("staff") Staff staff, @Param("id") Long id, @Param("confirmAmt") BigDecimal confirmAmt);

    void reject(@Param("staff") Staff staff, @Param("id") Long id);

    Order refundOrder(@Param("id") Long refundId);
}
