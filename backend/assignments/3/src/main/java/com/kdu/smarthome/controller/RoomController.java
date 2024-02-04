package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddRoomRequestDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller class for managing rooms in the SmartHome application.
 * Handles the addition of new rooms to a house.
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Adds a new room to a house in the system.
     *
     * @param authorization      The authorization token for authentication.
     * @param houseId             The identifier of the house to which the room will be added.
     * @param addRoomRequestDTO   The DTO containing details of the room to be added.
     * @return ResponseEntity with a RoomResponseDTO containing details of the added room.
     */
    @PostMapping
    public ResponseEntity<RoomResponseDTO> addRoomToHouse(@Valid @RequestHeader String authorization,
                                                          @Valid @RequestParam String houseId,
                                                          @Valid @RequestBody AddRoomRequestDTO addRoomRequestDTO) {
        return ResponseEntity.ok(roomService.addRoomToHouse(houseId, addRoomRequestDTO, authorization));
    }
}
