package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.UserRegisterRequestDTO;
import com.kdu.smarthome.dto.response.UserRegisterResponseDTO;
import com.kdu.smarthome.entity.User;
import com.kdu.smarthome.repo.UserRepository;
import com.kdu.smarthome.util.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user authentication-related operations.
 */
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    /**
     * Registers a new user with the provided details and generates a JWT token.
     *
     * @param userRegisterRequestDTO The user registration details.
     * @return A UserRegisterResponseDTO containing a success message and JWT token.
     */
    public UserRegisterResponseDTO register(UserRegisterRequestDTO userRegisterRequestDTO) {
        userRegisterRequestDTO.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));

        User user = repository.save(Mapper.mapToUser(userRegisterRequestDTO));

        String jwtToken = jwtService.generateToken(user);

        return new UserRegisterResponseDTO("User registered successfully", jwtToken);
    }
}
