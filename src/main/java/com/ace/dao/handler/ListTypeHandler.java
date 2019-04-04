package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by john on 16-10-21.
 */
public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> params, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.join(",", params));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String values = rs.getString(columnName);
        if (values == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(values);
        }

    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String values = rs.getString(columnIndex);
        if (values == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(values);
        }
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String values = cs.getString(columnIndex);
        if (values == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(values);
        }
    }
}
