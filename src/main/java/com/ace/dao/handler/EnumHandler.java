package com.ace.dao.handler;

import com.ace.entity.concern.enums.*;
import com.ace.entity.concern.invoice.InvoiceStatus;
import com.ace.entity.concern.invoice.InvoiceType;
import com.ace.entity.concern.invoice.MMethod;
import org.apache.ibatis.type.MappedTypes;

/**
 * Created by john on 16-10-21.
 */
@MappedTypes(value = {
        CouponExpired.class,
        CouponStatus.class,
        CouponType.class,
        OrderStatus.class,
        RoomCFM.class,
        RoomPublish.class,
        RoomRental.class,
        InvoiceStatus.class,
        InvoiceType.class,
        MMethod.class,
        RefundStatus.class
})
public class EnumHandler<E extends Enum<E>> extends BaseEnumTypeHandler {
    public EnumHandler(Class<E> type) {
        super(type);
    }
}