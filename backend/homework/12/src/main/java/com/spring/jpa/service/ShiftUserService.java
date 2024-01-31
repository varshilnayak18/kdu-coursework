package com.spring.jpa.service;

import com.spring.jpa.entity.Shift;
import com.spring.jpa.entity.ShiftUser;
import com.spring.jpa.exception.custom.DataNotFoundException;
import com.spring.jpa.exception.custom.ShiftNotFoundException;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import com.spring.jpa.repo.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
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
            shiftUserRepository.save(shiftUser);
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
            shiftUserList = shiftUserRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find shift-users for tenantId. Please check again.");
        }
        return shiftUserList;
    }

    public void deleteShiftUser(UUID shiftUserId){
        Optional<ShiftUser> shiftUserOptional = shiftUserRepository.findById(shiftUserId);
        if (shiftUserOptional.isPresent()) {
            ShiftUser shiftUser = shiftUserOptional.get();
            Shift shift = shiftUser.getShift();
            LocalTime time = LocalTime.of(23,0,0);
            if (shift != null && shift.getTimeEnd().toLocalTime().equals(time)) {
                shiftUserRepository.delete(shiftUser);
            } else {
                throw new ShiftNotFoundException("Shift does not end at 23:00 UTC");
            }
        } else {
            throw new DataNotFoundException("Cannot find shift-user for shiftUserId. Please check again.");
        }
    }
}
