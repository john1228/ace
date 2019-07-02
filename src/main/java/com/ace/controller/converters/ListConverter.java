package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class ListConverter implements Converter<String, List<String>> {
    @Override
    public List<String> convert(String s) {
        if (Strings.isBlank(s)) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(s.split(","));
        }
    }
}
