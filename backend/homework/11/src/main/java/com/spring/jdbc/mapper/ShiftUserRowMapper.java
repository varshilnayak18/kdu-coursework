package com.spring.jdbc.mapper;

import com.spring.jdbc.entity.ShiftUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping rows from a ResultSet to ShiftUser entities.
 * Implements the Spring JDBC RowMapper interface.
 */
public class ShiftUserRowMapper implements RowMapper<ShiftUser> {
    @Override
    public ShiftUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShiftUser shiftUser = new ShiftUser();
        shiftUser.setId(rs.getObject("id", UUID.class));
        shiftUser.setShiftId(rs.getObject("shift_id", UUID.class));
        shiftUser.setUserId(rs.getObject("user_id", UUID.class));
        shiftUser.setCreatedAt(rs.getTimestamp("created_at"));
        shiftUser.setCreatedBy(rs.getString("created_by"));
        shiftUser.setUpdatedAt(rs.getTimestamp("updated_at"));
        shiftUser.setUpdatedBy(rs.getString("updated_by"));
        shiftUser.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shiftUser;
    }
}
