package com.spring.jdbc.repository;

import com.spring.jdbc.entity.Shift;
import com.spring.jdbc.mapper.ShiftRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repository class for performing database operations related to Shift entities.
 * Utilizes Spring JDBC's JdbcTemplate for database interactions.
 */
@Repository
public class ShiftRepository {

    /**
     * JdbcTemplate for executing SQL queries and updates.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for ShiftRepository.
     * @param jdbcTemplate The JdbcTemplate instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Transactional method for adding a new Shift to the database.
     * @param shift The Shift object to be added.
     */
    @Transactional
    public void addShift(Shift shift) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shift.getShiftTypeId(), shift.getName(), shift.getDateStart(), shift.getDateEnd(), shift.getTimeStart(), shift.getTimeEnd(), shift.getCreatedAt(), shift.getCreatedBy(), shift.getUpdatedAt(), shift.getUpdatedBy(), shift.getTimeZone(), shift.getTenantId());
    }

    /**
     * Retrieves all Shift entities based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of Shift entities associated with the specified tenant.
     */
    public List<Shift> getAllShifts(UUID tenantId) {
        String sql = "SELECT * FROM shifts WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShiftRowMapper(), tenantId);
    }
}
