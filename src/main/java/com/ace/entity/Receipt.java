package com.ace.entity;


import com.alipay.api.internal.mapping.ApiField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Receipt {
    private Integer id;
    @ApiField("trade_no")
    private String no;
    @ApiField("out_trade_no")
    private String orderNo;
    @ApiField("seller_id")
    private String seller;
    @ApiField("buyer_id")
    private String buyer;
    @ApiField("total_amount")
    private BigDecimal price;
    private Date createdAt;
    private Date updatedAt;
}
