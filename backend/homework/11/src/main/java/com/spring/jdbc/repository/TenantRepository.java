package com.spring.jdbc.repository;

import com.spring.jdbc.entity.Tenant;
import com.spring.jdbc.mapper.TenantRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for performing database operations related to Tenant entities.
 * Utilizes Spring JDBC's JdbcTemplate for database interactions.
 */
@Repository
public class TenantRepository {

    /**
     * JdbcTemplate for executing SQL queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for TenantRepository.
     * @param jdbcTemplate The JdbcTemplate instance to be used for interacting with the database.
     */
    @Autowired
    public TenantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Retrieves all Tenants from the database.
     * @return List of Tenant entities.
     */
    public List<Tenant> getAllTenants() {
        String sql = "SELECT * FROM tenants";
        return jdbcTemplate.query(sql, new TenantRowMapper());
    }
}
