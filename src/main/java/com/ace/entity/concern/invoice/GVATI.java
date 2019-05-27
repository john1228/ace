package com.ace.entity.concern.invoice;

import lombok.Getter;
import lombok.Setter;

/**
 * @author john
 * @date 19-5-24 下午4:32
 */
@Setter
@Getter
public class GVATI extends IVBase {
    private String title;
    private String taxpayer;
    private String bank;
    private String bankNo;
    private String contact;
}
