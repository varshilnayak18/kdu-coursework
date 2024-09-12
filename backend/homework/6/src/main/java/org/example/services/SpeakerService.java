package org.example.services;

import org.example.entities.Speaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpeakerService {
    @Bean
    Speaker sonySpeaker(){
        return new Speaker("Sony",5000);
    }

    @Bean
    Speaker boseSpeaker(){
        return new Speaker("Bose",6000);
    }
}
