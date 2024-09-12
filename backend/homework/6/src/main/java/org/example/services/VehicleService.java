package org.example.services;

import jakarta.annotation.PostConstruct;
import org.example.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class VehicleService {
    private final List<Vehicle> vehicles = new ArrayList<>();

    @Autowired
    private TyreService tyreService;
    @Autowired
    private SpeakerService speakerService;

    @PostConstruct
    public void initialize(){
        generateVehicles();
    }

    public void generateVehicles(){
        boolean flag = true;
        for(int i = 0 ; i < Math.random()*100 ; i++){
            if(i%3 == 0){
                if(flag){
                    vehicles.add(new Vehicle(speakerService.sonySpeaker(),tyreService.bridgestoneTyre(),Math.random()*100000));
                }
                else {
                    vehicles.add(new Vehicle(speakerService.boseSpeaker(),tyreService.bridgestoneTyre(),Math.random()*100000));
                }
                flag = !flag;
            }
            else {
                if(flag){
                    vehicles.add(new Vehicle(speakerService.sonySpeaker(),tyreService.mrfTyre(),Math.random()*100000));
                }
                else {
                    vehicles.add(new Vehicle(speakerService.boseSpeaker(),tyreService.mrfTyre(),Math.random()*100000));
                }
            }
        }
    }

    public Vehicle findMostExpensive(){
        return vehicles.stream().sorted(Comparator.comparing(Vehicle::getTotalPrice).reversed()).limit(1).toList().get(0);
    }
}
