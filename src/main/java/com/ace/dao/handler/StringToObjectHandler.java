package com.ace.dao.handler;

import com.ace.entity.concern.enums.Week;
import com.ace.entity.concern.invoice.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by john on 16-10-21.
 */
@MappedTypes(value = {
        GVATI.class,
        VATI.class,
        Email.class,
        Express.class
})
public class StringToObjectHandler extends BaseTypeHandler<Object> {
    Logger logger = LoggerFactory.getLogger(StringToObjectHandler.class);

    @Resource
    ObjectMapper objectMapper;

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object o, JdbcType jdbcType) throws SQLException {
        try {
            String objStr = objectMapper.writeValueAsString(o);
            ps.setString(i, objStr);
        } catch (JsonProcessingException e) {
            throw new SQLException("类型转换错误");
        }
    }

    @Override
    public Object getNullableResult(ResultSet rs, String s) throws SQLException {
        String objStr = rs.getString(s);
        Object obj = null;
        try {
            obj = objectMapper.readValue(objStr, Object.class);
        } catch (IOException e) {
            throw new SQLException("类型转换错误");
        }
        return obj;
    }

    @Override
    public Object getNullableResult(ResultSet rs, int i) throws SQLException {
        String objStr = rs.getString(i);
        Object obj = null;
        try {
            obj = objectMapper.readValue(objStr, Object.class);
        } catch (IOException e) {
            throw new SQLException("类型转换错误");
        }
        return obj;
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int i) throws SQLException {
        String objStr = cs.getString(i);
        Object obj = null;
        try {
            obj = objectMapper.readValue(objStr, Object.class);
        } catch (IOException e) {
            throw new SQLException("类型转换错误");
        }
        return obj;
    }
}
