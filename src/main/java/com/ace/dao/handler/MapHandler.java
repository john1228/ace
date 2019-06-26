package com.ace.dao.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@MappedTypes(Map.class)
public class MapHandler extends BaseTypeHandler<Map<String, Object>> {
    Logger logger = LoggerFactory.getLogger(MapHandler.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> om, JdbcType it) throws SQLException {
        JSONObject obj = new JSONObject(om);
        logger.info("::" + obj.toString());
        ps.setString(i, obj.toString());
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String s) throws SQLException {
        try {
            String rst = rs.getString(s);
            return rst == null ? new HashMap<>() : objectMapper.readValue(rst, HashMap.class);
        } catch (Exception e) {
            throw new SQLException("类型错误");
        }
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int i) throws SQLException {
        try {
            String rst = rs.getString(i);
            return rst == null ? new HashMap<>() : objectMapper.readValue(rst, HashMap.class);
        } catch (Exception e) {
            throw new SQLException("类型错误");
        }
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int i) throws SQLException {
        try {
            String rst = cs.getString(i);
            return rst == null ? new HashMap<>() : objectMapper.readValue(rst, HashMap.class);
        } catch (Exception e) {
            throw new SQLException("类型错误");
        }
    }
}
