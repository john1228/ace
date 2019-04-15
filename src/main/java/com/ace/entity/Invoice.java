package com.ace.entity;

import com.ace.entity.concern.InvoiceUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jettison.json.JSONObject;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    private int id;
    private String orderNo;
    private InvoiceUtil.Type type;
    private JSONObject content;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private String remark;
    private Date createdAt;
    private Date updatedAt;
}
