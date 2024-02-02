package com.spring.assessment.controller;

import com.spring.assessment.entity.Address;
import com.spring.assessment.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private AddressService addressService;
    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<String> addAddress(@RequestBody Address address){
        addressService.addAddress(address);
        return ResponseEntity.ok("Address added successfully");
    }
}
