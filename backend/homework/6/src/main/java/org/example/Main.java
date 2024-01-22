package org.example;

import org.example.config.Config;
import org.example.entities.Vehicle;
import org.example.logging.Logging;
import org.example.services.VehicleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static Logging logger = new Logging();
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        VehicleService vehicleService = context.getBean(VehicleService.class);
        Vehicle vehicle = vehicleService.findMostExpensive();
        logger.logInfo("Most expensive vehicle: ");
        logger.logInfo("Total price: " + vehicle.getTotalPrice());
        logger.logInfo("Speaker: " + vehicle.getSpeaker().getBrandName());
        logger.logInfo("Tyre: " + vehicle.getTyre().getBrandName());
    }
}