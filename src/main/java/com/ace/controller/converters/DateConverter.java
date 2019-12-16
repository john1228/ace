package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.convert.converter.Converter;

import java.sql.Date;

/**
 * 字符串转日期转换器
 *
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        if (Strings.isBlank(s)) {
            return null;
        } else {
            return Date.valueOf(s);
        }

    }
}
