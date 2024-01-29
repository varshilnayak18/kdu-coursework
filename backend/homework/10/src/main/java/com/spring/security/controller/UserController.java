package com.spring.security.controller;

import com.spring.security.entity.UserEntity;
import com.spring.security.service.UserEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService){
        this.userEntityService = userEntityService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<String> getAllUsers(){
        return new ResponseEntity<>(userEntityService.getAll().toString(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<String> getUser(@RequestParam String name){
        return new ResponseEntity<>(userEntityService.getPersonUsername(name).toString(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntity userEntity){
        userEntityService.addPerson(userEntity);
        return new ResponseEntity<>("Added user successfully", HttpStatus.OK);
    }

}
