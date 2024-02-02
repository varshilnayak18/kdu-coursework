package com.spring.assessment.service;

import com.spring.assessment.dto.response.UserResponseDTO;
import com.spring.assessment.entity.User;
import com.spring.assessment.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO addUser(User user){
        userRepository.save(user);
        return new UserResponseDTO();
    }

    public User getPersonUsername(String username) {
        for(User u : userRepository.findAll()){
            if(u.getFullName().equals(username)){
                return u;
            }
        }
        throw new RuntimeException("Invalid Username");
    }
}
