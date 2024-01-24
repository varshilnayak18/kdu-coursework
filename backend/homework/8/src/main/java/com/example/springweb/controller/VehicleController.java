package com.example.springweb.controller;

import com.example.springweb.dto.request.VehicleRequestDTO;
import com.example.springweb.dto.response.VehicleResponseDTO;
import com.example.springweb.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add/vehicle")
    public String addVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO){
        return vehicleService.addVehicle(vehicleRequestDTO).getResponse();
    }

    @GetMapping("/search/vehicle")
    public VehicleResponseDTO searchVehicle(@RequestParam int id){
        return vehicleService.getVehicle(id);
    }

    @PutMapping("/update/vehicle/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(@PathVariable int id, @RequestBody VehicleRequestDTO newVehicle){
        return ResponseEntity.ok(vehicleService.updateVehicle(id,newVehicle));
    }

    @DeleteMapping("/delete/vehicle/{id}")
    public String deleteVehicle(@PathVariable int id){
        return vehicleService.removeVehicle(id).getResponse();
    }

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
