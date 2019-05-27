package com.ace.entity.concern.invoice;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john
 * @date 19-5-24 下午4:30
 * 普通发票
 */
@Getter
@Setter
public class VATI extends IVBase {
    private String type;
    private String title;
    private String taxpayer;
}
