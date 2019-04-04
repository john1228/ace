package com.ace.dao.handler;

import com.ace.dao.handler.types.Ref;
import com.ace.entity.concern.Organize;
import com.ace.entity.concern.Project;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
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
        Project.class,
        Organize.class
})
public class RefTypeHandler extends BaseTypeHandler<List<Ref>> {
    private static Gson gson = new Gson();
    private static JsonParser parser = new JsonParser();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Ref> params, JdbcType jdbcType) throws SQLException {
        ps.setString(i, gson.toJson(params));
    }

    @Override
    public List<Ref> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        List<Ref> refList = new ArrayList<>();
        JsonArray jsonArray = parser.parse(rs.getString(columnName)).getAsJsonArray();
        for (JsonElement element:jsonArray) {
            refList.add(gson.fromJson(element,Ref.class));
        }
        return refList;
    }

    @Override
    public List<Ref> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        List<Ref> refList = new ArrayList<>();
        JsonArray jsonArray = parser.parse(rs.getString(columnIndex)).getAsJsonArray();
        for (JsonElement element:jsonArray) {
            refList.add(gson.fromJson(element,Ref.class));
        }
        return refList;
    }

    @Override
    public List<Ref> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        List<Ref> refList = new ArrayList<>();
        JsonArray jsonArray = parser.parse(cs.getString(columnIndex)).getAsJsonArray();
        for (JsonElement element:jsonArray) {
            refList.add(gson.fromJson(element,Ref.class));
        }
        return refList;
    }
}
