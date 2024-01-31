package com.spring.jdbc.controller;

import com.spring.jdbc.dto.request.RequestDTO;
import com.spring.jdbc.entity.User;
import com.spring.jdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing User entities.
 * Handles HTTP requests related to User operations.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * Service class for User operations.
     */
    private final UserService userService;

    /**
     * Constructor for UserController.
     * @param userService The UserService instance to be used for handling User operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for adding a new User.
     * @param user The User object to be added.
     * @return ResponseEntity with a success message if the User is added successfully.
     */
    @PostMapping()
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving all Users based on the specified criteria.
     * @param requestDTO The RequestDTO containing criteria for retrieving Users.
     * @return ResponseEntity with a list of Users if retrieval is successful.
     */
    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestBody RequestDTO requestDTO) {
        List<User> users = userService.getAllUsers(requestDTO.getTenantId());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Endpoint for updating an existing User.
     * @param userId The unique identifier for the User to be updated.
     * @param user   The updated User object.
     * @return ResponseEntity with a success message if the User is updated successfully.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.ok("User updated successfully");
    }
}
