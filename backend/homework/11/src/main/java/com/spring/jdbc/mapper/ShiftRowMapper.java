package com.spring.jdbc.mapper;

import com.spring.jdbc.entity.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Mapper class for mapping rows from a ResultSet to Shift entities.
 * Implements the Spring JDBC RowMapper interface.
 */
public class ShiftRowMapper implements RowMapper<Shift> {
    @Override
    public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shift shift = new Shift();
        shift.setId(rs.getObject("id", UUID.class));
        shift.setShiftTypeId(rs.getObject("shift_type_id", UUID.class));
        shift.setName(rs.getString("name"));
        shift.setDateStart(rs.getDate("date_start"));
        shift.setDateEnd(rs.getDate("date_end"));
        shift.setTimeStart(rs.getTime("time_start"));
        shift.setTimeEnd(rs.getTime("time_end"));
        shift.setCreatedAt(rs.getTimestamp("created_at"));
        shift.setCreatedBy(rs.getString("created_by"));
        shift.setUpdatedAt(rs.getTimestamp("updated_at"));
        shift.setUpdatedBy(rs.getString("updated_by"));
        shift.setTimeZone(rs.getString("time_zone"));
        shift.setTenantId(rs.getObject("tenant_id", UUID.class));
        return shift;
    }
}
