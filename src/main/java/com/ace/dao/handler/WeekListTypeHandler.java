package com.ace.dao.handler;

import com.ace.entity.concern.enums.Week;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by john on 16-10-21.
 */
@MappedTypes(value = {
        Week.class
})
public class WeekListTypeHandler extends BaseTypeHandler<List<Week>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Week> weeks, JdbcType jdbcType) throws SQLException {
        List<String> enumList = new ArrayList<>();
        weeks.forEach(week -> enumList.add(week.name()));
        ps.setString(i, String.join(",", enumList));
    }

    @Override
    public List<Week> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String[] values = rs.getString(columnName).split(",");
        List<Week> weekList = new ArrayList<>();
        for (String value : values) {
            weekList.add(Week.valueOf(value));
        }
        return weekList;
    }

    @Override
    public List<Week> getNullableResult(ResultSet rs, int i) throws SQLException {
        String[] values = rs.getString(i).split(",");
        List<Week> weekList = new ArrayList<>();
        for (String value : values) {
            weekList.add(Week.valueOf(value));
        }
        return weekList;
    }

    @Override
    public List<Week> getNullableResult(CallableStatement cs, int i) throws SQLException {
        String[] values = cs.getString(i).split(",");
        List<Week> weekList = new ArrayList<>();
        for (String value : values) {
            weekList.add(Week.valueOf(value));
        }
        return weekList;
    }
}
