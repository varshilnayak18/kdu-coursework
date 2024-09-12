package com.spring.jdbc.service;

import com.spring.jdbc.entity.ShiftUser;
import com.spring.jdbc.exception.custom.DataNotFoundException;
import com.spring.jdbc.exception.custom.UnprocessableEntityException;
import com.spring.jdbc.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for performing business logic operations related to ShiftUser entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class ShiftUserService {

    /**
     * ShiftUserRepository for database operations related to ShiftUser entities.
     */
    private final ShiftUserRepository shiftUserRepository;

    /**
     * Constructor for ShiftUserService.
     * @param shiftUserRepository The ShiftUserRepository instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    /**
     * Adds a new ShiftUser to the database.
     * @param shiftUser The ShiftUser object to be added.
     * @throws UnprocessableEntityException If an exception occurs during the addition process.
     */
    public void addShiftUser(ShiftUser shiftUser) {
        try {
            shiftUserRepository.addShiftUser(shiftUser);
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add shift-user. Please check the entity.");
        }
    }

    /**
     * Retrieves all ShiftUsers based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of ShiftUser entities associated with the specified tenant.
     */
    public List<ShiftUser> getAllShiftUsers(UUID tenantId) {
        List<ShiftUser> shiftUserList;
        try {
            shiftUserList = shiftUserRepository.getAllShiftUsers(tenantId);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find shift-users for tenantId. Please check again.");
        }
        return shiftUserList;
    }
}
