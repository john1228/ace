package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.logging.log4j.util.Strings;
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
public class LongListHandler extends BaseTypeHandler<List<Long>> {
    Logger logger = LoggerFactory.getLogger(LongListHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Long> params, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.join(",", params.stream().map(integer -> integer.toString()).collect(Collectors.toList())));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toIntList(rs.getString(columnName));
    }

    @Override
    public List<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return toIntList(rs.getString(columnIndex));
    }

    @Override
    public List<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toIntList(cs.getString(columnIndex));
    }

    private List<Long> toIntList(String source) {
        if (source == null) {
            return new ArrayList<>();
        } else {
            logger.info("转换:" + source);
            List<Long> results = new ArrayList<>();
            String[] items = source.split(",");
            for (String item : items) {
                if (Strings.isNotBlank(item))
                    results.add(Long.parseLong(item));
            }
            return results;
        }
    }
}
