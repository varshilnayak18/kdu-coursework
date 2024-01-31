package com.spring.jpa.controller;

import com.spring.jpa.dto.request.RequestDTO;
import com.spring.jpa.entity.ShiftUser;
import com.spring.jpa.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<String> addShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.addShiftUser(shiftUser);
        return new ResponseEntity<>("Shift-user added successfully", HttpStatus.CREATED);
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

    @DeleteMapping("/{shiftUserId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID shiftUserId) {
        shiftUserService.deleteShiftUser(shiftUserId);
        return new ResponseEntity<>("Shift-user deleted successfully", HttpStatus.OK);
    }
}
