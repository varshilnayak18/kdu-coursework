package org.example.services;

import org.example.entities.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    @Bean
    Tyre mrfTyre(){
        return new Tyre("MRF", 10000);
    }
    @Bean
    Tyre bridgestoneTyre(){
        return new Tyre("Bridgestone", 9000);
    }
}
