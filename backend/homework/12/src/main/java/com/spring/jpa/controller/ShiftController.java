package com.spring.jpa.controller;

import com.spring.jpa.dto.request.RequestDTO;
import com.spring.jpa.entity.Shift;
import com.spring.jpa.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing Shift entities.
 * Handles HTTP requests related to Shift operations.
 */
@RestController
@RequestMapping("/shift")
public class ShiftController {

    /**
     * Service class for Shift operations.
     */
    private final ShiftService shiftService;

    /**
     * Constructor for ShiftController.
     * @param shiftService The ShiftService instance to be used for handling Shift operations.
     */
    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    /**
     * Endpoint for adding a new Shift.
     * @param shift The Shift object to be added.
     * @return ResponseEntity with a success message if the Shift is added successfully.
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody Shift shift) {
        shiftService.addShift(shift);
        return new ResponseEntity<>("Shift added successfully", HttpStatus.CREATED);
    }

    /**
     * Endpoint for retrieving all Shifts based on the specified criteria.
     * @param requestDTO The RequestDTO containing criteria for retrieving Shifts.
     * @return ResponseEntity with a list of Shifts if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<Shift>> getAllShifts(@RequestBody RequestDTO requestDTO) {
        List<Shift> shifts = shiftService.getAllShifts(requestDTO.getTenantId());
        return ResponseEntity.ok(shifts);
    }

    @GetMapping("/top3")
    public ResponseEntity<List<Shift>> getTop3ShiftsByDateRange() {
        List<Shift> shifts = shiftService.getTop3ShiftsByDateRange();
        return ResponseEntity.ok(shifts);
    }
}
