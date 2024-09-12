package com.spring.jdbc.controller;

import com.spring.jdbc.dto.request.RequestDTO;
import com.spring.jdbc.entity.Shift;
import com.spring.jdbc.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok("Shift added successfully");
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
}
