package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by john on 16-10-21.
 */
public class StringListHandler extends BaseTypeHandler<List<String>> {
    Logger logger = LoggerFactory.getLogger(StringListHandler.class);


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> params, JdbcType jdbcType) throws SQLException {
        logger.info("设置");
        StringBuilder sb = new StringBuilder();
        params.forEach(item -> {
            if (sb.length() == 0) {
                sb.append(item);
            } else {
                sb.append(",").append(item);
            }
        });
        ps.setString(i, sb.toString());
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        if (result == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(result.split(","));
        }

    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        if (result == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(result.split(","));
        }
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if (result == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(result.split(","));
        }
    }
}
