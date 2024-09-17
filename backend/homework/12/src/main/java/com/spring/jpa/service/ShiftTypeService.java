package com.spring.jpa.service;

import com.spring.jpa.entity.ShiftType;
import com.spring.jpa.exception.custom.DataNotFoundException;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import com.spring.jpa.repo.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for performing business logic operations related to ShiftType entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class ShiftTypeService {

    /**
     * ShiftTypeRepository for database operations related to ShiftType entities.
     */
    private final ShiftTypeRepository shiftTypeRepository;

    /**
     * Constructor for ShiftTypeService.
     * @param shiftTypeRepository The ShiftTypeRepository instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * Adds a new ShiftType to the database.
     * @param shiftType The ShiftType object to be added.
     * @throws UnprocessableEntityException If an exception occurs during the addition process.
     */
    public void addShiftType(ShiftType shiftType) {
        try {
            shiftTypeRepository.save(shiftType);
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add shift-type. Please check the entity.");
        }
    }

    /**
     * Retrieves all ShiftTypes based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of ShiftType entities associated with the specified tenant.
     */
    public List<ShiftType> getAllShiftTypes(UUID tenantId) {
        List<ShiftType> shiftTypeList;
        try {
            shiftTypeList = shiftTypeRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find shift-types for tenantId. Please check again.");
        }
        return shiftTypeList;
    }
}
