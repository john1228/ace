package com.ace.util.wxpay;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author john
 * @date 19-5-18 下午8:18
 */
@Setter
@Getter
@RequiredArgsConstructor
public class WxpayRequest {
    @NonNull
    private String body;
    @NonNull
    private String outTradeNo;
    @NonNull
    private String totalFee;
}
