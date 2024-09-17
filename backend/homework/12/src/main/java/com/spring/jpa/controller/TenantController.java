package com.spring.jpa.controller;

import com.spring.jpa.entity.Tenant;
import com.spring.jpa.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing Tenant entities.
 * Handles HTTP requests related to Tenant operations.
 */
@RestController
@RequestMapping("/tenant")
public class TenantController {

    /**
     * Service class for Tenant operations.
     */
    private final TenantService tenantService;

    /**
     * Constructor for TenantController.
     * @param tenantService The TenantService instance to be used for handling Tenant operations.
     */
    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping()
    public ResponseEntity<String> addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
        return new ResponseEntity<>("Tenant added successfully", HttpStatus.CREATED);
    }
    /**
     * Endpoint for retrieving all Tenants.
     * @return ResponseEntity with a list of Tenants if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }
}
