package com.spring.jpa.service;

import com.spring.jpa.entity.Shift;
import com.spring.jpa.exception.custom.DataNotFoundException;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import com.spring.jpa.repo.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service class for performing business logic operations related to Shift entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class ShiftService {

    /**
     * ShiftRepository for database operations related to Shift entities.
     */
    private final ShiftRepository shiftRepository;

    /**
     * Constructor for ShiftService.
     * @param shiftRepository The ShiftRepository instance to be used for interacting with the database.
     */
    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    /**
     * Adds a new Shift to the database.
     * @param shift The Shift object to be added.
     * @throws UnprocessableEntityException If an exception occurs during the addition process.
     */
    public void addShift(Shift shift) {
        try {
            shiftRepository.save(shift);
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add shift. Please check the entity.");
        }
    }

    /**
     * Retrieves all Shifts based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of Shift entities associated with the specified tenant.
     */
    public List<Shift> getAllShifts(UUID tenantId) {
        List<Shift> shiftList;
        try {
            shiftList = shiftRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find shifts for tenantId. Please check again.");
        }
        return shiftList;
    }

    public List<Shift> getTop3ShiftsByDateRange(){
        List<Shift> shiftList;
        LocalDate dateStart = LocalDate.of(2023, 1, 1);
        LocalDate dateEnd = LocalDate.of(2023, 1, 25);
        try {
            shiftList = shiftRepository.findTop3ShiftsByDateRange(dateStart,dateEnd);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find shifts for date range");
        }
        return shiftList;
    }
}
