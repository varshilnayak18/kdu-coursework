package com.example.springweb.controller;

import com.example.springweb.dto.request.VehicleRequestDTO;
import com.example.springweb.dto.response.VehicleResponseDTO;
import com.example.springweb.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
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
     * @return string indicating method outcome
     */
    @PostMapping("/add/vehicle")
    public String addVehicle(@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO){
        return vehicleService.addVehicle(vehicleRequestDTO).getResponse();
    }

    /**
     * searches for a vehicle given id and returns if found
     * @param id id of vehicle to be searched
     * @return DTO of vehicle for response
     */
    @GetMapping("/search/vehicle")
    public VehicleResponseDTO searchVehicle(@RequestParam int id){
        return vehicleService.getVehicle(id);
    }

    /**
     * takes in id and updated details and updates the vehicle
     * @param id id of vehicle
     * @param newVehicle updated vehicle details
     * @return updated vehicle data
     */
    @PutMapping("/update/vehicle/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable int id, @Valid @RequestBody VehicleRequestDTO newVehicle){
        return ResponseEntity.ok(vehicleService.updateVehicle(id,newVehicle));
    }

    /**
     * takes in id and deletes the vehicle
     * @param id id of vehicle
     * @return string indicating method output
     */
    @DeleteMapping("/delete/vehicle/{id}")
    public String deleteVehicle(@PathVariable int id){
        return vehicleService.removeVehicle(id).getResponse();
    }

    /**
     * takes the type as input and fetches vehicle based on that
     * @param type type can be highest or lowest
     * @return DTO of vehicle
     */
    @GetMapping("/get/vehicle/{type}")
    public VehicleResponseDTO getVehicle(@PathVariable String type){
        if(type.equals("highest")){
            return vehicleService.getHighLowVehicle(1);
        }
        else {
            return vehicleService.getHighLowVehicle(0);
        }
    }
}
