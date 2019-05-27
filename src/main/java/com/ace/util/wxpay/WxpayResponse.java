package com.ace.util.wxpay;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class WxpayResponse {
    @NonNull
    private String appid;
    @NonNull
    private String partnerid;
    @NonNull
    private String prepayid;
    @NonNull
    @JsonProperty("package")
    private String pack;
    @NonNull
    private String noncestr;
    @NonNull
    private Long timestamp;
    @NonNull
    private String sign;
}
