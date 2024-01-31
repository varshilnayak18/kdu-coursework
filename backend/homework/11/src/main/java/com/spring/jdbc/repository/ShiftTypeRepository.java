package com.spring.jdbc.repository;

import com.spring.jdbc.entity.ShiftType;
import com.spring.jdbc.mapper.ShiftTypeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repository class for performing database operations related to ShiftType entities.
 * Utilizes Spring JDBC's JdbcTemplate for database interactions.
 */
@Repository
public class ShiftTypeRepository {

    /**
     * JdbcTemplate for executing SQL queries and updates.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for ShiftTypeRepository.
     * @param jdbcTemplate The JdbcTemplate instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftTypeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Transactional method for adding a new ShiftType to the database.
     * @param shiftType The ShiftType object to be added.
     */
    @Transactional
    public void addShiftType(ShiftType shiftType) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftType.getUniqueName(), shiftType.getDescription(), shiftType.isActive(), shiftType.getCreatedAt(), shiftType.getCreatedBy(), shiftType.getUpdatedAt(), shiftType.getUpdatedBy(), shiftType.getTimeZone(), shiftType.getTenantId());
    }

    /**
     * Retrieves all ShiftType entities based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of ShiftType entities associated with the specified tenant.
     */
    public List<ShiftType> getAllShiftTypes(UUID tenantId) {
        String sql = "SELECT * FROM shift_types WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShiftTypeRowMapper(),tenantId);
    }
}
