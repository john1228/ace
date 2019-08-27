package com.ace.service.admin;

import com.ace.controller.admin.concerns.DataTable;
import com.ace.controller.admin.concerns.PayCriteria;
import com.ace.entity.Alipay;
import com.ace.entity.Wxpay;

public interface WxpayService {

    void data(DataTable<Wxpay> dataTable, PayCriteria criteria);

    Wxpay findById(String projectId);

    void create(Wxpay wxpay);

    void update(Wxpay wxpay);

    void delete(String projectId);
}
