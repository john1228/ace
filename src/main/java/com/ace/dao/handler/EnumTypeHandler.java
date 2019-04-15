package com.ace.dao.handler;

import com.ace.entity.concern.IEnum;
import com.ace.entity.concern.OrderUtil;
import com.ace.entity.coupon.concern.CouponUtil;
import com.ace.entity.room.concern.DeviceUtil;
import com.ace.entity.room.concern.RoomUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 16-10-21.
 */
@MappedTypes(value = {
        OrderUtil.Status.class,
        CouponUtil.Status.class,
        CouponUtil.Type.class,
        CouponUtil.Expired.class,
        RoomUtil.Rental.class,
        RoomUtil.Type.class,
        DeviceUtil.Status.class
})
public class EnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {
    private Class<E> type;
    private Map<Integer, E> map = new HashMap<Integer, E>();

    public EnumTypeHandler() { }

    public EnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        E[] enums = type.getEnumConstants();
        if (enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
        for (E e : enums) {
            IEnum iEnum = (IEnum) e;
            map.put(iEnum.getCode(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        IEnum iEnum = (IEnum) parameter;
        ps.setInt(i, iEnum.getCode());
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int i = rs.getInt(columnName);
        if (rs.wasNull()) {
            return null;
        } else {
            return getIEnum(i);
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            return getIEnum(i);
        }
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            return getIEnum(i);
        }
    }

    private E getIEnum(int code) {
        try {
            return map.get(code);
        } catch (Exception ex) {
            throw new IllegalArgumentException(
                    "Cannot convert " + code + " to " + type.getSimpleName() + " by value.", ex);
        }
    }
}
