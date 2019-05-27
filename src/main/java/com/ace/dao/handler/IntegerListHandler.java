package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by john on 16-10-21.
 */
public class IntegerListHandler extends BaseTypeHandler<List<Integer>> {
    Logger logger = LoggerFactory.getLogger(IntegerListHandler.class);


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> params, JdbcType jdbcType) throws SQLException {
        logger.info("设置");
        StringBuilder sb = new StringBuilder();
        params.forEach(item -> sb.append(item.toString()));
        ps.setString(i, sb.toString());
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Scanner scanner = new Scanner(rs.getString(columnName));
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;

    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Scanner scanner = new Scanner(rs.getString(columnIndex));
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Scanner scanner = new Scanner(cs.getString(columnIndex));
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list;
    }
}
