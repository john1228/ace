package com.ace.dao.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 16-10-21.
 */
public class JSONHandler extends BaseTypeHandler<JSONObject> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONObject oj, JdbcType it) throws SQLException {
        ps.setString(i, oj.toString());
    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, String s) throws SQLException {
        String rst = rs.getString(s);
        try {
            return rst == null ? new JSONObject() : new JSONObject(rst);
        } catch (JSONException e) {
            throw new SQLException("类型转换错误");
        }

    }

    @Override
    public JSONObject getNullableResult(ResultSet rs, int i) throws SQLException {
        String rst = rs.getString(i);
        try {
            return rst == null ? new JSONObject() : new JSONObject(rst);
        } catch (JSONException e) {
            throw new SQLException("类型转换错误");
        }
    }

    @Override
    public JSONObject getNullableResult(CallableStatement cs, int i) throws SQLException {
        String rst = cs.getString(i);
        try {
            return rst == null ? new JSONObject() : new JSONObject(rst);
        } catch (JSONException e) {
            throw new SQLException("类型转换错误");
        }
    }
}
