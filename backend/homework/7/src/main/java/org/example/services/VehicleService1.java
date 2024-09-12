package org.example.services;

import org.example.entities.Inventory;
import org.example.entities.Speaker;
import org.example.entities.Tyre;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Comparator;

public class VehicleService1 implements VehicleService{

    private final Inventory inventory;

    /**
     * Use of field injection
     */
    @Autowired
    private SpeakerService speakerService;

    private TyreService tyreService;

    /**
     * Use of setter injection
     */
    @Autowired
    public void setTyreService(TyreService tyreService) {
        this.tyreService = tyreService;
    }

    /**
     * Use of constructor injection
     */
    @Autowired
    public VehicleService1(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * @param n input number of vehicles
     * generates list of vehicles and add to its own inventory
     */
    @Override
    public void generateVehicles(int n){
        boolean flag = true;
        Tyre tyre;
        Speaker speaker;
        for(int i = 0 ; i < n ; i++){
            double hikePercent = 1.1;
            if(i%2 == 0){
                tyre = tyreService.bridgestoneTyre();
            }
            else {
                tyre = tyreService.mrfTyre();
            }
            if(flag){
                speaker = speakerService.sonySpeaker();
            }
            else {
                speaker = speakerService.boseSpeaker();
            }
            flag = !flag;
            inventory.getVehicles().add(new Vehicle(speaker,tyre,Math.random()*100000 + tyre.getPrice()* hikePercent + speaker.getPrice()* hikePercent));
        }
    }

    /**
     * @return Vehicle which has the highest value amongst the list
     */
    @Override
    public Vehicle findMostExpensive() {
        return inventory.getVehicles().stream().max(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }

    /**
     * @return Vehicle which has the lowest value amongst the list
     */
    @Override
    public Vehicle findLeastExpensive() {
        return inventory.getVehicles().stream().min(Comparator.comparing(Vehicle::getPrice)).orElse(null);
    }
}
