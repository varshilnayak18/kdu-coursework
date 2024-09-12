package org.example;

import org.example.config.Config;
import org.example.entities.Vehicle;
import org.example.logging.Logging;
import org.example.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class Main {
    static Logging logger = new Logging();
    static Scanner scanner = new Scanner(System.in);


    /**
     * use of @Qualifier
     */
    @Autowired
    @Qualifier("factory1")
    private VehicleService factory1;

    @Autowired
    @Qualifier("factory2")
    private VehicleService factory2;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Main main = context.getBean(Main.class);
        main.run();
    }
    public void run(){
        logger.logInfo("Enter number of vehicles to generate: ");
        int n = scanner.nextInt();
        factory1.generateVehicles(n);
        factory2.generateVehicles(n);
        logger.logInfo(n + " vehicles generated in both factories!\n");

        logger.logInfo("Factory 1 overview: ");
        Vehicle vehicle1 = factory1.findMostExpensive();
        logger.logInfo("Most expensive vehicle: ");
        printVehicle(vehicle1);
        vehicle1 = factory1.findLeastExpensive();
        logger.logInfo("Least expensive vehicle: ");
        printVehicle(vehicle1);

        logger.logInfo("\nFactory 2 overview: ");
        Vehicle vehicle2 = factory2.findMostExpensive();
        logger.logInfo("Most expensive vehicle: ");
        printVehicle(vehicle2);
        vehicle2 = factory2.findLeastExpensive();
        logger.logInfo("Least expensive vehicle: ");
        printVehicle(vehicle2);
    }

    public void printVehicle(Vehicle vehicle){
        logger.logInfo("Total price: " + vehicle.getPrice());
        logger.logInfo("Speaker: " + vehicle.getSpeaker().getBrandName());
        logger.logInfo("Tyre: " + vehicle.getTyre().getBrandName());
    }
}