package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import com.ace.controller.api.concerns.ListStatus;
import com.ace.entity.concern.enums.OrderStatus;
import org.apache.logging.log4j.util.Strings;
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
public class ListStatusConverter implements Converter<String, ListStatus> {

    @Override
    public ListStatus convert(String s) {
        if (Strings.isBlank(s)) {
            return ListStatus.ALL;
        } else {
            Optional<ListStatus> optional = Arrays.stream(ListStatus.values()).filter(status -> status.getCode() == Integer.parseInt(s)).findFirst();
            if (optional.isPresent()) {
                return optional.get();
            } else {
                return ListStatus.ALL;
            }
        }

    }
}
