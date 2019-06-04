package com.ace.util.wxpay;

import com.ace.controller.api.concerns.ApiView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author john
 * @date 19-5-18 下午8:18
 */
@Setter
@Getter
@RequiredArgsConstructor
public class WxpayResponse {
    @NonNull
    @JsonView(ApiView.Detail.class)
    private String appid;
    @NonNull
    @JsonView(ApiView.Detail.class)
    private String partnerid;
    @NonNull
    @JsonView(ApiView.Detail.class)
    private String prepayid;
    @NonNull
    @JsonProperty("package")
    @JsonView(ApiView.Detail.class)
    private String pack;
    @NonNull
    @JsonView(ApiView.Detail.class)
    private String noncestr;
    @NonNull
    @JsonView(ApiView.Detail.class)
    private Long timestamp;
    @NonNull
    @JsonView(ApiView.Detail.class)
    private String sign;

    public SortedMap<String, Object> signMap() {
        SortedMap<String, Object> payParameters = new TreeMap<>();
        payParameters.put("appid", appid);
        payParameters.put("partnerid", partnerid);
        payParameters.put("prepayid", prepayid);
        payParameters.put("package", pack);
        payParameters.put("noncestr", noncestr);
        payParameters.put("timestamp", timestamp);
        return payParameters;
    }
}
