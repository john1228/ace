package com.ace.entity.concern;

import com.ace.controller.api.concerns.ApiView;
import com.ace.util.wxpay.WxpayResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

/**
 * @author john
 * @date 19-6-4 上午11:31
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    @JsonView(ApiView.Detail.class)
    private String alipay;

    @JsonView(ApiView.Detail.class)
    private WxpayResponse wxpay;

    public Payment(String alipay, WxpayResponse wxpay) {
        this.alipay = alipay;
        this.wxpay = wxpay;
    }

}
