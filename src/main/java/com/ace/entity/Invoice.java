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
    private Long orderId;
    private InvoiceType type;
    private Map<String, Object> content;
    private MMethod method;
    private Map<String, Object> address;
    private InvoiceStatus status = InvoiceStatus.PENDING;
    private String remark;
    private Map<String, Object> express;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedAt;
}
