package com.spring.security.service;

import com.spring.security.entity.UserEntity;
import com.spring.security.exception.custom.CustomException;
import com.spring.security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    UserEntityRepository userEntityRepository;


    @Autowired
    public UserEntityService(UserEntityRepository userEntityRepository){
        this.userEntityRepository = userEntityRepository;
    }

    public void addPerson(UserEntity userEntity){
        userEntityRepository.addUserEntity(userEntity);
    }

    public List<UserEntity> getAll(){
        return userEntityRepository.getAllUserEntities();
    }
    public UserEntity getPersonUsername(String name){
        for(UserEntity u : userEntityRepository.getAllUserEntities()){
            if(u.getUserName().equalsIgnoreCase(name)){
                return u;
            }
        }

        throw new CustomException("Invalid Username");
    }

}