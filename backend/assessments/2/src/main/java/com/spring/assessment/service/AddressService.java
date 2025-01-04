package com.spring.assessment.service;

import com.spring.assessment.entity.Address;
import com.spring.assessment.exception.custom.UnprocessableEntityException;
import com.spring.assessment.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void addAddress(Address address){
        try {
            addressRepository.save(address);
        }
        catch (Exception e){
            throw new UnprocessableEntityException("Address cannot be added please check the entity again.");
        }
    }
}
