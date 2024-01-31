package com.spring.jdbc.controller;

import com.spring.jdbc.dto.request.RequestDTO;
import com.spring.jdbc.entity.ShiftUser;
import com.spring.jdbc.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for managing ShiftUser entities.
 * Handles HTTP requests related to ShiftUser operations.
 */
@RestController
@RequestMapping("/shift-user")
public class ShiftUserController {

    /**
     * Service class for ShiftUser operations.
     */
    private final ShiftUserService shiftUserService;

    /**
     * Constructor for ShiftUserController.
     * @param shiftUserService The ShiftUserService instance to be used for handling ShiftUser operations.
     */
    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     * Endpoint for adding a new ShiftUser.
     * @param shiftUser The ShiftUser object to be added.
     * @return ResponseEntity with a success message if the ShiftUser is added successfully.
     */
    @PostMapping()
    public ResponseEntity<String> addShift(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return ResponseEntity.ok("Shift-user added successfully");
    }

    /**
     * Endpoint for retrieving all ShiftUsers based on the specified criteria.
     * @param requestDTO The RequestDTO containing criteria for retrieving ShiftUsers.
     * @return ResponseEntity with a list of ShiftUsers if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<ShiftUser>> getAllShiftUsers(@RequestBody RequestDTO requestDTO) {
        List<ShiftUser> shiftUsers = shiftUserService.getAllShiftUsers(requestDTO.getTenantId());
        return ResponseEntity.ok(shiftUsers);
    }
}
