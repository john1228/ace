package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by john on 16-10-21.
 */
public class IntegerListHandler extends BaseTypeHandler<List<Integer>> {
    Logger logger = LoggerFactory.getLogger(IntegerListHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> params, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.join(",", params.stream().map(integer -> integer.toString()).collect(Collectors.toList())));
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toIntList(rs.getString(columnName));
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return toIntList(rs.getString(columnIndex));
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toIntList(cs.getString(columnIndex));
    }

    private List<Integer> toIntList(String source) {
        if (source == null) {
            return new ArrayList<>();
        } else {
            List<Integer> results = new ArrayList<>();
            String[] items = source.split(",");
            for (String item : items) {
                results.add(Integer.valueOf(item));
            }
            return results;
        }
    }

    public static void main(String[] args) {
        List<Integer> strs = Arrays.asList(1, 2, 3, 4);
        for (Integer integer : strs) {
            System.err.println(integer.toString());
        }
    }
}
