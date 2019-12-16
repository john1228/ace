package com.ace.util.wxpay;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author john
 * @date 19-5-18 下午8:18
 */
@Setter
@Getter
@RequiredArgsConstructor
public class WxpayRefundRequest {
    @NonNull
    private String orderNo;
    @NonNull
    private BigDecimal refundFee;
    @NonNull
    private BigDecimal totalFee;
}
