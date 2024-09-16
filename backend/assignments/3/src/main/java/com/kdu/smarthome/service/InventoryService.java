package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.InventoryRequestDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.exception.custom.FetchFailedException;
import com.kdu.smarthome.exception.custom.UnprocessableEntityException;
import com.kdu.smarthome.repo.InventoryRepository;
import com.kdu.smarthome.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for handling inventory-related operations.
 */
@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves a list of all inventory items.
     *
     * @return InventoryResponseDTO containing the list of inventory items.
     * @throws FetchFailedException If fetching the inventory list fails.
     */
    public InventoryResponseDTO getInventoryItems() {
        try {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            return Mapper.mapToInventoryResponseDTO(inventoryList);
        } catch (Exception e) {
            throw new FetchFailedException("Failed to fetch inventory list");
        }
    }

    /**
     * Adds a new item to the inventory.
     *
     * @param inventoryRequestDTO DTO containing details for adding an item to the inventory.
     * @return ResponseDTO with the result of adding the item to the inventory.
     * @throws UnprocessableEntityException If adding the item to the inventory fails.
     */
    public ResponseDTO addInventoryItem(InventoryRequestDTO inventoryRequestDTO) {
        try {
            return Mapper.mapToResponseDTO(
                    inventoryRepository.save(Mapper.mapToInventory(inventoryRequestDTO)),
                    "Item added to Inventory successfully"
            );
        } catch (Exception e) {
            throw new UnprocessableEntityException("Item cannot be added, please check again");
        }
    }
}
