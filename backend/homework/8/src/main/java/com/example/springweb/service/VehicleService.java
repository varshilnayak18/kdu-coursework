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

    /**
     * add vehicle to the inventory
     * @param vehicleRequestDTO request DTO
     * @return response DTO
     */
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

    /**
     * fetches vehicle from the inventory
     * @param id id of vehicle
     * @return response DTO
     */
    public VehicleResponseDTO getVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            return new VehicleResponseDTO(vehicleNotExist);
        }
        return getVehicleResponseFromVehicle(vehicle,"Vehicle fetched");
    }

    /**
     * updates vehicle details in the inventory using id
     * @param id id of vehicle
     * @param newVehicle request DTO for updated details
     * @return response DTO
     */
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

    /**
     * deletes vehicle from the inventory
     * @param id id of vehicle
     * @return response DTO
     */
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

    /**
     * takes in type and fetches data accordingly
     * @param type type of function to execute
     * @return DTO of vehicle
     */
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

    /**
     * converts request DTO to vehicle
     */
    public Vehicle getVehicleFromRequestDTO(VehicleRequestDTO vehicleRequestDTO){
        return new Vehicle(vehicleRequestDTO.getId(),vehicleRequestDTO.getCarBrandName(),vehicleRequestDTO.getSpeaker(),vehicleRequestDTO.getTyre(),vehicleRequestDTO.getPrice());
    }

    /**
     * converts vehicle to response DTO
     */
    public VehicleResponseDTO getVehicleResponseFromVehicle(Vehicle vehicle, String response){
        return new VehicleResponseDTO(vehicle.getId(),vehicle.getCarBrandName(),vehicle.getSpeaker(),vehicle.getTyre(),vehicle.getPrice(),response);
    }
}
