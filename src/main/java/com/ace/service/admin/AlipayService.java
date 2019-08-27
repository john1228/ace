package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.Alipay;

public interface AlipayService {

    void data(DataTable<Alipay> dataTable, PayCriteria criteria);

    Alipay findById(String projectId);

    void create(Alipay alipay);

    void update(Alipay alipay);

    void delete(String projectId);
}
