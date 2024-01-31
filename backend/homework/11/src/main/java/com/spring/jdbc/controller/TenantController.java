package com.spring.jdbc.controller;

import com.spring.jdbc.dto.request.AllEntitiesDTO;
import com.spring.jdbc.entity.Tenant;
import com.spring.jdbc.service.TenantService;
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

    /**
     * Endpoint for retrieving all Tenants.
     * @return ResponseEntity with a list of Tenants if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    /**
     * Endpoint for adding all Tenant entities using the provided DTO.
     * @param dto The AllEntitiesDTO containing details to be added for Tenants.
     * @return ResponseEntity with a success message if addition is successful.
     */
    @PostMapping()
    public ResponseEntity<String> addAllTenantEntities(@RequestBody AllEntitiesDTO dto) {
        tenantService.addAllTenantEntities(dto);
        return new ResponseEntity<>("All details added successfully", HttpStatus.OK);
    }
}
