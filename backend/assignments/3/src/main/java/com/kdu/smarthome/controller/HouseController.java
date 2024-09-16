package com.kdu.smarthome.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.dto.request.HouseRegisterRequestDTO;
import com.kdu.smarthome.dto.request.UpdateAddressRequestDTO;
import com.kdu.smarthome.dto.request.AddUserToHouseRequestDTO;
import com.kdu.smarthome.dto.response.GetAllHousesResponseDTO;
import com.kdu.smarthome.dto.response.HouseRegisterResponseDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.dto.response.GetRoomsDevicesResponseDTO;
import com.kdu.smarthome.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller class for managing houses in the SmartHome application.
 * Handles house registration, user addition, retrieval, address update, and fetching house details.
 */
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Registers a new house in the system.
     *
     * @param authorization           The authorization token in the request header.
     * @param houseRegisterRequestDTO The DTO containing house registration details.
     * @return ResponseEntity with a HouseRegisterResponseDTO indicating the result of the registration.
     */
    @PostMapping
    public ResponseEntity<HouseRegisterResponseDTO> addHouse(@Valid @RequestHeader String authorization,
                                                             @Valid @RequestBody HouseRegisterRequestDTO houseRegisterRequestDTO) {
        return ResponseEntity.ok(houseService.addHouse(houseRegisterRequestDTO, authorization));
    }

    /**
     * Adds a user to a house in the system.
     *
     * @param authorization               The authorization token in the request header.
     * @param houseId                     The ID of the house to which the user will be added.
     * @param addUserToHouseRequestDTO    The DTO containing details for adding a user to a house.
     * @return ResponseEntity with a ResponseDTO indicating the result of the user addition.
     * @throws JsonProcessingException    If an error occurs during JSON processing.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<ResponseDTO> addUserToHouse(@Valid @RequestHeader String authorization,
                                                      @Valid @PathVariable String houseId,
                                                      @Valid @RequestBody AddUserToHouseRequestDTO addUserToHouseRequestDTO)
            throws JsonProcessingException {
        return ResponseEntity.ok(houseService.addUserToHouse(houseId, addUserToHouseRequestDTO, authorization));
    }

    /**
     * Retrieves all houses in the system.
     *
     * @return ResponseEntity with a GetAllHousesResponseDTO containing details of all houses.
     */
    @GetMapping
    public ResponseEntity<GetAllHousesResponseDTO> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    /**
     * Updates the address of a house in the system.
     *
     * @param authorization     The authorization token in the request header.
     * @param houseId           The ID of the house for which the address will be updated.
     * @param houseDTO          The DTO containing the updated house address.
     * @return ResponseEntity with a ResponseDTO indicating the result of the address update.
     */
    @PutMapping
    public ResponseEntity<ResponseDTO> updateHouseAddress(@Valid @RequestHeader String authorization,
                                                          @Valid @RequestParam String houseId,
                                                          @Valid @RequestBody UpdateAddressRequestDTO houseDTO) {
        return ResponseEntity.ok(houseService.updateHouseAddress(houseId, houseDTO, authorization));
    }

    /**
     * Retrieves details of a specific house in the system.
     *
     * @param houseId   The ID of the house for which details will be retrieved.
     * @return ResponseEntity with a GetRoomsDevicesResponseDTO containing details of the specified house.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<GetRoomsDevicesResponseDTO> getHouseDetails(@Valid @PathVariable("houseId") String houseId) {
        return ResponseEntity.ok(houseService.getHouseDetails(houseId));
    }
}
