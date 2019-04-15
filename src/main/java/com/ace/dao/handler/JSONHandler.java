package com.ace.dao.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by john on 16-10-21.
 */
public class JSONHandler extends BaseTypeHandler<JSONObject> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.toString());
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.wasNull() ? new JSONObject() : new JSONObject(rs.getString(columnName));
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        try {
            return rs.wasNull() ? new JSONObject() : new JSONObject(rs.getString(columnIndex));
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        try {
            return cs.wasNull() ? new JSONObject() : new JSONObject(cs.getString(columnIndex));
        } catch (JSONException e) {
            return new JSONObject();
        }
    }

}
