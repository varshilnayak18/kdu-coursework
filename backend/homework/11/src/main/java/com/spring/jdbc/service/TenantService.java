package com.spring.jdbc.service;

import com.spring.jdbc.dto.request.AllEntitiesDTO;
import com.spring.jdbc.entity.*;
import com.spring.jdbc.exception.custom.UnprocessableEntityException;
import com.spring.jdbc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for performing business logic operations related to Tenant entities and associated entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class TenantService {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final ShiftUserRepository shiftUserRepository;
    private final ShiftTypeRepository shiftTypeRepository;
    private final ShiftRepository shiftRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository, UserRepository userRepository, ShiftUserRepository shiftUserRepository, ShiftTypeRepository shiftTypeRepository, ShiftRepository shiftRepository) {
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.shiftUserRepository = shiftUserRepository;
        this.shiftTypeRepository = shiftTypeRepository;
        this.shiftRepository = shiftRepository;
    }

    /**
     * Retrieves all Tenants from the database.
     * @return List of Tenant entities.
     */
    public List<Tenant> getAllTenants() {
        return tenantRepository.getAllTenants();
    }

    /**
     * Transactional method for adding all tenant-related entities to the database.
     * @param dto The AllEntitiesDTO containing entities to be added.
     * @throws UnprocessableEntityException If an exception occurs during the addition process.
     */
    @Transactional
    public void addAllTenantEntities(AllEntitiesDTO dto) {
        try {
            userRepository.addUser(dto.getUser());
            shiftRepository.addShift(dto.getShift());
            shiftTypeRepository.addShiftType(dto.getShiftType());
            shiftUserRepository.addShiftUser(dto.getShiftUser());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add details. Please check the entities.");
        }
    }
}
