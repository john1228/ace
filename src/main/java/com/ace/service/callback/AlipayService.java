package com.ace.service.callback;

import java.util.Map;

/**
 * @author john
 * @date 19-6-17 上午11:55
 */
public interface AlipayService {
    boolean check(String orderNo, Map<String, String> params);
}
