package com.example.springweb.service;

import com.example.springweb.dto.request.VehicleRequestDTO;
import com.example.springweb.dto.response.VehicleResponseDTO;
import com.example.springweb.exceptions.custom.InvalidArgumentException;
import com.example.springweb.repository.*;
import com.example.springweb.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Slf4j
@Service
public class VehicleService {
    private static final String VEHICLE_NOT_EXIST = "Vehicle with given id does not exist";
    private final VehicleRepository inventory;
    @Autowired
    public VehicleService(VehicleRepository inventory){
        this.inventory = inventory;
    }

    /**
     * add vehicle to the inventory if it passes the check else throws an exception
     * @param vehicleRequestDTO request DTO
     */
    public void addVehicle(VehicleRequestDTO vehicleRequestDTO){
        Vehicle vehicle = inventory.getVehicle(vehicleRequestDTO.getId());
        if(Objects.isNull(vehicle)){
            vehicle = getVehicleFromRequestDTO(vehicleRequestDTO);
        }
        else {
            log.error("Vehicle with same id already exists");
            throw new InvalidArgumentException("Vehicle with same id already exists");
        }
        inventory.addVehicle(vehicle);
        log.info("Vehicle added successfully");
    }

    /**
     * fetches vehicle from the inventory if its there else throws an exception
     * @param id id of vehicle
     * @return response DTO
     */
    public VehicleResponseDTO getVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            log.error(VEHICLE_NOT_EXIST);
            throw new InvalidArgumentException(VEHICLE_NOT_EXIST);
        }
        log.info("Vehicle fetched successfully");
        return getVehicleResponseFromVehicle(vehicle);
    }

    /**
     * updates vehicle details in the inventory using id after check is passes else throws an exception
     * @param id id of vehicle
     * @param newVehicle request DTO for updated details
     */
    public void updateVehicle(int id, VehicleRequestDTO newVehicle){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            log.error(VEHICLE_NOT_EXIST);
            throw new InvalidArgumentException(VEHICLE_NOT_EXIST);
        }
        else {
            Vehicle nVehicle = getVehicleFromRequestDTO(newVehicle);
            inventory.updateVehicle(vehicle,nVehicle);
            log.info("Vehicle updated successfully");
        }
    }

    /**
     * deletes vehicle from the inventory if found else throws an exception
     * @param id id of vehicle
     */
    public void removeVehicle(int id){
        Vehicle vehicle = inventory.getVehicle(id);
        if(Objects.isNull(vehicle)){
            log.error(VEHICLE_NOT_EXIST);
            throw new InvalidArgumentException(VEHICLE_NOT_EXIST);
        }
        else {
            inventory.deleteVehicle(vehicle);
            log.info("Vehicle deleted successfully");
        }
    }

    /**
     * takes in type and fetches data accordingly
     * if not enough data it throws an exception
     * @param type type of function to execute
     * @return DTO of vehicle
     */
    public VehicleResponseDTO getHighLowVehicle(int type){
        if(inventory.getVehicles().isEmpty()){
            log.error("No vehicles in the inventory currently");
            throw new NullPointerException("No vehicles in the inventory currently");
        }
        Vehicle vehicle;
        if(type == 1){
            vehicle = inventory.getMostExpensive();
        }
        else {
            vehicle = inventory.getLeastExpensive();
        }
        log.info("Vehicle fetched successfully");
        return getVehicleResponseFromVehicle(vehicle);
    }

    /**
     * converts request DTO to vehicle
     */
    public Vehicle getVehicleFromRequestDTO(VehicleRequestDTO vehicleRequestDTO){
        return new Vehicle(vehicleRequestDTO.getId(),vehicleRequestDTO.getCarBrandName(),new Speaker(vehicleRequestDTO.getSpeakerBrandName()),new Tyre(vehicleRequestDTO.getTyreBrandName()),vehicleRequestDTO.getPrice());
    }

    /**
     * converts vehicle to response DTO
     */
    public VehicleResponseDTO getVehicleResponseFromVehicle(Vehicle vehicle){
        return new VehicleResponseDTO(vehicle.getId(),vehicle.getCarBrandName(),vehicle.getPrice());
    }
}
