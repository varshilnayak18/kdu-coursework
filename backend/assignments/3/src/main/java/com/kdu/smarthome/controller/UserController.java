package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.request.UserRegisterRequestDTO;
import com.kdu.smarthome.dto.response.UserRegisterResponseDTO;
import com.kdu.smarthome.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller class for user authentication and registration in the SmartHome application.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Handles user registration in the system.
     *
     * @param userRegisterRequestDTO The DTO containing user registration details.
     * @return ResponseEntity with a UserRegisterResponseDTO containing registration success details.
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> registerUser(@Valid @RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(userRegisterRequestDTO));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
