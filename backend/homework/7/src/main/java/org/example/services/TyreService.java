package org.example.services;

import org.example.constants.Brands;
import org.example.entities.Tyre;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TyreService {
    /**
     * @return mrfTyre bean
     */
    @Bean
    Tyre mrfTyre(){
        return new Tyre(Brands.MRF, 10000);
    }

    /**
     * @return bridgestoneTyre bean
     */
    @Bean
    Tyre bridgestoneTyre(){
        return new Tyre(Brands.BRIDGESTONE, 9000);
    }
}
