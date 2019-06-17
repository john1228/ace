package com.ace.service.callback;

import java.util.SortedMap;

/**
 * @author john
 * @date 19-6-17 上午11:55
 */
public interface WxpayService {
    boolean check(String orderNo, SortedMap<String, Object> params, String sign);
}
