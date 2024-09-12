package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Vehicle> vehicles = new ArrayList<>();

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
