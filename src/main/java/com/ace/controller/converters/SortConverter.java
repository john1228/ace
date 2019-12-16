package com.ace.controller.converters;

import com.ace.annotation.TypeConverter;
import com.ace.controller.admin.concerns.Sort;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.List;


/**
 * 列表排序转换器
 * @author john
 * @date 19-5-7 下午5:41
 */
@TypeConverter
public class SortConverter implements Converter<String, Sort> {
    @Override
    public Sort convert(String s) {
        try {
            String[] info = s.split("_");
            return new Sort(info[0], info[1]);
        } catch (Exception exp) {
            return null;
        }
    }
}
