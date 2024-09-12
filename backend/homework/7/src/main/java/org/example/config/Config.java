package org.example.config;
import org.example.Main;
import org.example.entities.Inventory;
import org.example.services.VehicleService;
import org.example.services.VehicleService1;
import org.example.services.VehicleService2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan({"org.example.services"})
public class Config {
    @Bean
    @Scope("prototype")
    Inventory inventory(){
        return new Inventory();
    }

    @Bean("factory1")
    VehicleService factory1(){
        return new VehicleService1(inventory());
    }

    @Bean("factory2")
    VehicleService factory2(){
        return new VehicleService2(inventory());
    }

    @Bean
    Main main1(){
        return new Main();
    }
}

