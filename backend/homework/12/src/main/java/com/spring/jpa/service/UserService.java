package com.spring.jpa.service;

import com.spring.jpa.entity.User;
import com.spring.jpa.exception.custom.DataNotFoundException;
import com.spring.jpa.exception.custom.UnprocessableEntityException;
import com.spring.jpa.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class for performing business logic operations related to User entities.
 * Acts as an intermediary between the controller and repository layers.
 */
@Service
public class UserService {

    /**
     * UserRepository for database operations related to User entities.
     */
    private final UserRepository userRepository;

    /**
     * Constructor for UserService.
     * @param userRepository The UserRepository instance to be used for interacting with the database.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new User to the database.
     * @param user The User object to be added.
     * @throws UnprocessableEntityException If an exception occurs during the addition process.
     */
    public void addUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UnprocessableEntityException("Cannot add user. Please check the entity.");
        }
    }

    /**
     * Retrieves all Users based on the specified tenant ID.
     * @param tenantId The unique identifier for the tenant.
     * @return List of User entities associated with the specified tenant.
     */
    public List<User> getAllUsers(UUID tenantId) {
        List<User> userList;
        try {
            userList = userRepository.findAllByTenantId(tenantId);
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find users for tenantId. Please check again.");
        }
        return userList;
    }

    /**
     * Updates an existing User in the database.
     * @param userId The unique identifier for the User to be updated.
     * @param user   The updated User object.
     * @throws DataNotFoundException If the specified user is not found in the database.
     */
    public void updateUser(UUID userId, User user) {
        try {
            int cnt = 0;
            cnt = userRepository.updateUserDetails(userId,user.getUsername(),user.getLoggedIn(),user.getTimeZone());
            if(cnt == 0){
                throw new DataNotFoundException("Cannot find user with given userId. Please check again.");
            }
        } catch (Exception e) {
            throw new DataNotFoundException("Cannot find user with given userId. Please check again.");
        }
    }

    public Page<User> getAllUsersPagination(Pageable pageable){
        return userRepository.findAll(pageable);
    }
}
