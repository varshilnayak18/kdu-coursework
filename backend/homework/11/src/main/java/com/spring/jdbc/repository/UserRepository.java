package com.spring.jdbc.repository;

import com.spring.jdbc.entity.User;
import com.spring.jdbc.mapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * Repository class for performing database operations related to User entities.
 * Utilizes Spring JDBC's JdbcTemplate for database interactions.
 */
@Repository
public class UserRepository {

    /**
     * JdbcTemplate for executing SQL queries and updates.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for UserRepository.
     * @param jdbcTemplate The JdbcTemplate instance to be used for interacting with the database.
     */
    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Transactional method for adding a new User to the database.
     * @param user The User object to be added.
     */
    @Transactional
    public void addUser(User user) {
        String sql = "INSERT INTO users (id, username, loggedin, created_at, created_by, updated_at, updated_by, time_zone, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), user.getUsername(), user.getLoggedIn(), new Timestamp(System.currentTimeMillis()), user.getCreatedBy(), user.getUpdatedAt(), user.getUpdatedBy(), user.getTimeZone(), user.getTenantId());
    }

    /**
     * Retrieves all Users based on the specified tenant ID.
     *
     * @param tenantId The unique identifier for the tenant.
     * @return List of User entities associated with the specified tenant.
     */
    public List<User> getAllUsers(UUID tenantId) {
        String sql = "SELECT * FROM users WHERE tenant_id = ?";
        return jdbcTemplate.query(sql,new UserRowMapper(),tenantId);
    }

    /**
     * Updates an existing User in the database.
     * @param userId The unique identifier for the User to be updated.
     * @param user   The updated User object.
     */
    public void updateUser(UUID userId, User user) {
        String sql = "UPDATE users SET username = ?, tenant_id = ?, updated_by = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getTenantId(), user.getUpdatedBy(), new Timestamp(System.currentTimeMillis()), userId);
    }
}
