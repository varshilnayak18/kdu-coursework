package com.spring.assessment.controller;

import com.spring.assessment.dto.response.UserResponseDTO;
import com.spring.assessment.entity.User;
import com.spring.assessment.security.CustomAuthManager;
import com.spring.assessment.security.TokenGeneratorFilter;
import com.spring.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private final CustomAuthManager customAuthManager;
    private final TokenGeneratorFilter tokenGeneratorFilter;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, CustomAuthManager customAuthManager, TokenGeneratorFilter tokenGeneratorFilter, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.customAuthManager = customAuthManager;
        this.tokenGeneratorFilter = tokenGeneratorFilter;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return ResponseEntity.ok("registered");
    }
    @GetMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user){
        try {
            Authentication authentication = customAuthManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getFullName(), user.getPassword())
            );
            String token = tokenGeneratorFilter.generateJWT(authentication);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
