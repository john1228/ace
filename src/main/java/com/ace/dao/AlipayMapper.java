package com.ace.dao;

import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.Alipay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author john
 * @date 19-6-6 上午9:32
 */
public interface AlipayMapper {
    Long recordsTotal(@Param("criteria") PayCriteria criteria);

    List<Alipay> dataList(@Param("criteria") PayCriteria criteria);

    Alipay findBy(@Param("projectId") String projectId);

    void create(Alipay alipay);

    void update(Alipay alipay);

    void delete(@Param("projectId") String projectId);
}
