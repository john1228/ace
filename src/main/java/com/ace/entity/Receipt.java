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
    private Integer id;
    private String no;
    private String orderNo;
    private String seller;
    private String buyer;
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;

    public Receipt(Map<String, String> params) {
        this.no = params.get("trade_no");
        this.orderNo = params.get("out_trade_no");
        this.seller = params.get("seller_id");
        this.buyer = params.get("buyer_id");
        this.price = new BigDecimal(params.get("total_amount"));
    }
}
