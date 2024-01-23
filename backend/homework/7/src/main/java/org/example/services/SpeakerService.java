package org.example.services;

import org.example.constants.Brands;
import org.example.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {

    /**
     * @return sonySpeaker bean
     */
    @Bean
    Speaker sonySpeaker(){
        return new Speaker(Brands.SONY,5000);
    }

    /**
     * @return boseSpeaker bean
     */
    @Bean
    Speaker boseSpeaker(){
        return new Speaker(Brands.BOSE,6000);
    }
}
