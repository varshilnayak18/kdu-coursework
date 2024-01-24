package com.example.springweb.service;

import com.example.springweb.dto.request.VehicleRequestDTO;
import com.example.springweb.dto.response.VehicleResponseDTO;
import com.example.springweb.repository.*;
import com.example.springweb.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class VehicleService {
    private String vehicleNotExist = "Vehicle with given id does not exist";
    private final VehicleRepository inventory;
    @Autowired
    public VehicleService(VehicleRepository inventory){
        this.inventory = inventory;
    }

    public VehicleResponseDTO addVehicle(VehicleRequestDTO vehicleRequestDTO){
        Vehicle vehicle = inventory.getVehicle(vehicleRequestDTO.getId());
        if(Objects.isNull(vehicle)){
            vehicle = getVehicleFromRequestDTO(vehicleRequestDTO);
        }
        else {
            return getVehicleResponseFromVehicle(vehicle,"Vehicle with same id already exists");
        }
        inventory.addVehicle(vehicle);
        return getVehicleResponseFromVehicle(vehicle, "Vehicle added successfully");
    }

    public VehicleResponseDTO getVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResponseDTO(vehicleNotExist);
        }
        return getVehicleResponseFromVehicle(vehicle,"Vehicle fetched");
    }

    public VehicleResponseDTO updateVehicle(int id,VehicleRequestDTO newVehicle){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResponseDTO(vehicleNotExist);
        }
        else {
            Vehicle nVehicle = getVehicleFromRequestDTO(newVehicle);
            inventory.updateVehicle(vehicle,nVehicle);
            return getVehicleResponseFromVehicle(nVehicle,"Vehicle updated successfully");
        }
    }

    public VehicleResponseDTO removeVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResponseDTO(vehicleNotExist);
        }
        else {
            inventory.deleteVehicle(vehicle);
            return getVehicleResponseFromVehicle(vehicle,"Vehicle deleted successfully");
        }
    }

    public VehicleResponseDTO getHighLowVehicle(int type){
        Vehicle vehicle;
        if(type == 1){
            vehicle = inventory.getMostExpensive();
        }
        else {
            vehicle = inventory.getLeastExpensice();
        }
        return getVehicleResponseFromVehicle(vehicle,"");
    }

    public Vehicle getVehicleFromRequestDTO(VehicleRequestDTO vehicleRequestDTO){
        return new Vehicle(vehicleRequestDTO.getId(),vehicleRequestDTO.getCarBrandName(),vehicleRequestDTO.getSpeaker(),vehicleRequestDTO.getTyre(),vehicleRequestDTO.getPrice());
    }

    public VehicleResponseDTO getVehicleResponseFromVehicle(Vehicle vehicle, String response){
        return new VehicleResponseDTO(vehicle.getId(),vehicle.getCarBrandName(),vehicle.getSpeaker(),vehicle.getTyre(),vehicle.getPrice(),response);
    }
}
