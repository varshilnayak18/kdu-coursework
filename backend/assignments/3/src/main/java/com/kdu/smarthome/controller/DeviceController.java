package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.AddDeviceRequestDTO;
import com.kdu.smarthome.dto.request.DeviceRegisterRequestDTO;
import com.kdu.smarthome.dto.response.ResponseDTO;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller class for managing devices in the SmartHome application.
 * Handles device registration and addition to houses.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Registers a new device in the system.
     *
     * @param authorization           The authorization token in the request header.
     * @param deviceRegisterRequestDTO The DTO containing device registration details.
     * @return ResponseEntity with a ResponseDTO indicating the result of the registration.
     */
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerDevice(@Valid @RequestHeader String authorization,
                                                      @Valid @RequestBody DeviceRegisterRequestDTO deviceRegisterRequestDTO) {
        return ResponseEntity.ok(deviceService.registerDevice(deviceRegisterRequestDTO, authorization));
    }

    /**
     * Adds a device to a house in the system.
     *
     * @param authorization       The authorization token in the request header.
     * @param addDeviceRequestDTO The DTO containing details for adding a device to a house.
     * @return ResponseEntity with a ResponseDTO indicating the result of the addition.
     */
    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addDeviceToHouse(@Valid @RequestHeader String authorization,
                                                        @Valid @RequestBody AddDeviceRequestDTO addDeviceRequestDTO) {
        return ResponseEntity.ok(deviceService.addDeviceToHouse(addDeviceRequestDTO, authorization));
    }
}
