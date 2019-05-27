package com.ace.service.api;

import com.ace.entity.Account;
import com.ace.entity.Invoice;

/**
 * @author john
 * @date 19-5-14 下午3:34
 */
public interface InvoiceService {
    void create(Account account, String orderNo, Invoice invoice);

    Invoice show(String orderNo);
}
