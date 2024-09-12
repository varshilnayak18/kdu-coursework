package com.spring.jdbc.controller;

import com.spring.jdbc.dto.request.RequestDTO;
import com.spring.jdbc.entity.ShiftType;
import com.spring.jdbc.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller class for managing ShiftType entities.
 * Handles HTTP requests related to ShiftType operations.
 */
@RestController
@RequestMapping("/shift-type")
public class ShiftTypeController {

    /**
     * Service class for ShiftType operations.
     */
    private final ShiftTypeService shiftTypeService;

    /**
     * Constructor for ShiftTypeController.
     * @param shiftTypeService The ShiftTypeService instance to be used for handling ShiftType operations.
     */
    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     * Endpoint for adding a new ShiftType.
     * @param shiftType The ShiftType object to be added.
     * @return ResponseEntity with a success message if the ShiftType is added successfully.
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody ShiftType shiftType) {
        shiftTypeService.addShiftType(shiftType);
        return ResponseEntity.ok("Shift-type added successfully");
    }

    /**
     * Endpoint for retrieving all ShiftTypes based on the specified criteria.
     * @param requestDTO The RequestDTO containing criteria for retrieving ShiftTypes.
     * @return ResponseEntity with a list of ShiftTypes if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<ShiftType>> getAllShifts(@RequestBody RequestDTO requestDTO) {
        List<ShiftType> shiftTypes = shiftTypeService.getAllShiftTypes(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftTypes);
    }
}
