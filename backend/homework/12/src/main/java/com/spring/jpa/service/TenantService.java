package com.spring.jpa.service;

import com.spring.jpa.entity.*;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import com.spring.jpa.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for performing business logic operations related to Tenant entities and associated entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void addTenant(Tenant tenant) {
        try {
            tenantRepository.save(tenant);
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add tenant. Please check the entity.");
        }
    }
    /**
     * Retrieves all Tenants from the database.
     * @return List of Tenant entities.
     */
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }
}
