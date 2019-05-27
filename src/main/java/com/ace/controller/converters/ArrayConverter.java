package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.List;


/**
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class ArrayConverter implements Converter<String, List<String>> {
    Logger logger = LoggerFactory.getLogger(ArrayConverter.class);

    @Override
    public List<String> convert(String s) {
        logger.info(":::" + s);
        return Arrays.asList(s.split(","));
    }
}
