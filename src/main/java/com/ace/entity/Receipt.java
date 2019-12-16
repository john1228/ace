package com.ace.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class Receipt {
    /**
     * 流水编号
     */
    private Integer id;
    /**
     * 第三方支付流水编号
     */
    private String no;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 收款账户
     */
    private String seller;
    /**
     * 付款账户
     */
    private String buyer;
    /**
     * 付款金额
     */
    private BigDecimal price;
    /**
     * 流水创建日期
     */
    private Date createdAt;
    /**
     * 流水更新日期
     */
    private Date updatedAt;

    public Receipt(Map<String, String> params) {
        this.no = params.get("trade_no");
        this.orderNo = params.get("out_trade_no");
        this.seller = params.get("seller_id");
        this.buyer = params.get("buyer_id");
        this.price = new BigDecimal(params.get("total_amount"));
    }
}
