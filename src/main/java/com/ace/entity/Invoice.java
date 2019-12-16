package com.ace.entity;

import com.ace.entity.concern.invoice.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Invoice {
    private int id;
    /**
     * 关联订单编号
     **/
    private Long orderId;
    /**
     * 类型 {@link InvoiceType}
     */
    private InvoiceType type;
    /**
     * 内容
     */
    private Map<String, Object> content;
    /**
     * 邮寄方式 {@link MMethod}
     */
    private MMethod method;
    /**
     * 邮寄地址
     */
    private Map<String, Object> address;
    /**
     * 状态 {@link InvoiceStatus}
     */
    private InvoiceStatus status = InvoiceStatus.PENDING;
    /**
     * 备注信息
     */
    private String remark;
    /**
     * 邮寄信息
     */
    private Map<String, Object> express;
    /**
     * 申请时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    /**
     * 修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
