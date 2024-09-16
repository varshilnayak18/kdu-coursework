package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.InventoryRequestDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class for managing inventory items in the SmartHome application.
 * Handles retrieval of inventory items and addition of new inventory items.
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Retrieves all inventory items in the system.
     *
     * @return ResponseEntity with an InventoryResponseDTO containing details of all inventory items.
     */
    @GetMapping
    public ResponseEntity<InventoryResponseDTO> getInventoryItems() {
        return ResponseEntity.ok(inventoryService.getInventoryItems());
    }

    /**
     * Adds a new inventory item to the system.
     *
     * @param inventoryRequestDTO The DTO containing details of the inventory item to be added.
     * @return ResponseEntity with a ResponseDTO indicating the result of the inventory item addition.
     */
    @PostMapping
    public ResponseEntity<ResponseDTO> addInventoryItem(@Valid @RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(inventoryRequestDTO));
    }
}
