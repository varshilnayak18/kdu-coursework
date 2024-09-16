package com.spring.security.repository;

import com.spring.security.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserEntityRepository {
    List<UserEntity> userEntityList;

    public UserEntityRepository() {
        this.userEntityList = new ArrayList<>();
    }

    public void addUserEntity(UserEntity userEntity){
        userEntityList.add(userEntity);
    }

    public List<UserEntity> getAllUserEntities(){
        return userEntityList;
    }
}
