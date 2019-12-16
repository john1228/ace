package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 字符串转换时间戳
 *
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class TimestampConverter implements Converter<String, Timestamp> {

    @Override
    public Timestamp convert(String s) {
        if (Strings.isBlank(s)) {
            return new Timestamp(System.currentTimeMillis());
        } else {
            if (s.split(":").length == 2) {
                return Timestamp.valueOf(s + ":00");
            } else {
                return Timestamp.valueOf(s);
            }
        }

    }
}
