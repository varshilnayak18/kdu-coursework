package com.spring.jdbc.repository;

import com.spring.jdbc.entity.ShiftUser;
import com.spring.jdbc.mapper.ShiftUserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Repository class for performing database operations related to ShiftUser entities.
 * Utilizes Spring JDBC's JdbcTemplate for database interactions.
 */
@Repository
public class ShiftUserRepository {

    /**
     * JdbcTemplate for executing SQL queries and updates.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for ShiftUserRepository.
     * @param jdbcTemplate The JdbcTemplate instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Transactional method for adding a new ShiftUser to the database.
     * @param shiftUser The ShiftUser object to be added.
     */
    @Transactional
    public void addShiftUser(ShiftUser shiftUser) {
        String sql = "INSERT INTO shift_user (id, shift_id, user_id, created_at, created_by, updated_at, updated_by, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), shiftUser.getShiftId(), shiftUser.getUserId(), shiftUser.getCreatedAt(), shiftUser.getCreatedBy(), shiftUser.getUpdatedAt(), shiftUser.getUpdatedBy(), shiftUser.getTenantId());
    }

    /**
     * Retrieves all ShiftUser entities based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of ShiftUser entities associated with the specified tenant.
     */
    public List<ShiftUser> getAllShiftUsers(UUID tenantId) {
        String sql = "SELECT * FROM shift_user WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new ShiftUserRowMapper(),tenantId);
    }
}
