package com.example.springweb.repository;

import com.example.springweb.entities.Vehicle;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Repository
public class VehicleRepository {
    List<Vehicle> vehicles = new ArrayList<>();

    /**
     * returns vehicle using id
     * @param id id of vehicle
     * @return vehicle object
     */
    public Vehicle getVehicle(int id){
        for(Vehicle v: vehicles){
            if(v.getId() == id){
                return v;
            }
        }
        return null;
    }

    /**
     * adds vehicle to inventory list
     * @param vehicle vehicle object
     */
    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }

    /**
     * updates vehicle object given new vehicle object
     * @param vehicle vehicle object
     * @param newVehicle new vehicle object to be updated
     */
    public void updateVehicle(Vehicle vehicle,Vehicle newVehicle){
        int index = vehicles.indexOf(vehicle);
        vehicles.set(index,newVehicle);
    }

    /**
     * deletes vehicle from inventory list
     * @param vehicle vehicle object
     */
    public void deleteVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    /**
     * returns the most expensive vehicle from inventory list
     * @return vehicle object
     */
    public Vehicle getMostExpensive(){
        return vehicles.stream().max(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }

    /**
     * returns the least expensive vehicle from inventory list
     * @return vehicle object
     */
    public Vehicle getLeastExpensive(){
        return vehicles.stream().min(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }
}
