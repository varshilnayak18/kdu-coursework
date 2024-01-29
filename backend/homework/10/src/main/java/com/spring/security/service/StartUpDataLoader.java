package com.spring.security.service;

import com.spring.security.entity.UserEntity;
import com.spring.security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class StartUpDataLoader implements CommandLineRunner {

    UserEntityRepository userEntityRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public StartUpDataLoader(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
        this.userEntityRepository = userEntityRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        userEntityRepository.addUserEntity(new UserEntity("varshil", passwordEncoder.encode("password"), "ROLE_ADMIN"));
        userEntityRepository.addUserEntity(new UserEntity("nipun", passwordEncoder.encode("password"), "ROLE_BASIC"));
    }
}

