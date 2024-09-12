package com.example.springweb.controller;

import com.example.springweb.dto.request.VehicleRequestDTO;
import com.example.springweb.dto.response.VehicleResponseDTO;
import com.example.springweb.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    /**
     * setter injection
     */
    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    /**
     * adds vehicle to inventory if id is unique
     * @param vehicleRequestDTO post request data
     * @return string indicating method outcome if successful else errorDTO
     */
    @PostMapping
    public ResponseEntity<String> addVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO){
        vehicleService.addVehicle(vehicleRequestDTO);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    /**
     * searches for a vehicle given id and returns if found
     * @param id id of vehicle to be searched
     * @return DTO of vehicle for response
     */
    @GetMapping
    public VehicleResponseDTO searchVehicle(@RequestParam int id){
        return vehicleService.getVehicle(id);
    }

    /**
     * takes in id and updated details and updates the vehicle
     * @param id id of vehicle
     * @param newVehicle updated vehicle details
     * @return string indicating method outcome if successful else errorDTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @Valid @RequestBody VehicleRequestDTO newVehicle){
        vehicleService.updateVehicle(id,newVehicle);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    /**
     * takes in id and deletes the vehicle
     * @param id id of vehicle
     * @return string indicating method outcome if successful else errorDTO
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable int id){
        vehicleService.removeVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    /**
     * takes the type as input and fetches vehicle based on that
     * @param type type can be highest or lowest
     * @return DTO of vehicle if successful else errorDTO
     */
    @GetMapping("/{type}")
    public VehicleResponseDTO getVehicle(@PathVariable String type){
        if(type.equals("highest")){
            return vehicleService.getHighLowVehicle(1);
        }
        else {
            return vehicleService.getHighLowVehicle(0);
        }
    }
}
