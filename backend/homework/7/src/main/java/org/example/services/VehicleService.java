package org.example.services;

import org.example.entities.Vehicle;

public interface VehicleService {

    public void generateVehicles(int n);

    public Vehicle findMostExpensive();

    public Vehicle findLeastExpensive();
}
