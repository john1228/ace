package com.ace.dao;

import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.Wxpay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author john
 * @date 19-6-6 上午9:32
 */
public interface WxpayMapper {
    Long recordsTotal(@Param("criteria") PayCriteria criteria);

    List<Wxpay> dataList(@Param("criteria") PayCriteria criteria);

    Wxpay findBy(@Param("projectId") String projectId);

    void create(Wxpay wxpay);

    void update(Wxpay wxpay);

    void delete(@Param("projectId") String projectId);
}
