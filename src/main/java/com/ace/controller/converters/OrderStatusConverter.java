package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import com.ace.entity.concern.enums.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class OrderStatusConverter implements Converter<String, OrderStatus> {
    Logger logger = LoggerFactory.getLogger(OrderStatusConverter.class);

    @Override
    public OrderStatus convert(String s) {
        Optional<OrderStatus> filter = Arrays.stream(OrderStatus.values()).filter(status -> status.getName().equals(s)).findFirst();
        if (filter.isPresent()) {
            return filter.get();
        } else {
            return null;
        }
    }
}
