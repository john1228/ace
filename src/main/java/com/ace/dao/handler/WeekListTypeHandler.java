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
        return toWeekList(rs.getString(columnName));
    }

    @Override
    public List<Week> getNullableResult(ResultSet rs, int i) throws SQLException {
        return toWeekList(rs.getString(i));
    }

    @Override
    public List<Week> getNullableResult(CallableStatement cs, int i) throws SQLException {
        return toWeekList(cs.getString(i));
    }

    private List<Week> toWeekList(String weeks) {
        if (weeks == null) {
            return new ArrayList<>();
        } else {
            List<Week> weekList = new ArrayList<>();
            String[] weekAry = weeks.split(",");
            for (String week : weekAry) {
                weekList.add(Week.valueOf(week));
            }
            return weekList;
        }
    }
}
