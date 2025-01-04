package com.spring.assessment.config;

import com.spring.assessment.security.CustomAuthManager;
import com.spring.assessment.security.TokenGeneratorFilter;
import com.spring.assessment.security.TokenValidatorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@ComponentScan("com.spring.security")
public class SecurityConfig {

    private final CustomAuthManager authManager;
    private String adminType = "ADMIN";
    private String basicType = "BASIC";
    @Autowired
    public SecurityConfig(CustomAuthManager authManager){
        this.authManager = authManager;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authManager);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/**").hasAnyRole(adminType, basicType)
                        .anyRequest().authenticated()).csrf().disable();
        http.httpBasic(withDefaults());
        return http.build();
    }

}

