package com.keitam.kayak.service;

import com.keitam.kayak.model.KayakUser;
import com.keitam.kayak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

//    public Optional<KayakUser> getKayakUser(String userName, String password){
//        return Optional.ofNullable(userRepository.getByUserID(Long.parseLong(userName)));
//
//    }

    public List<KayakUser> getAll() {
        return userRepository.findAll();
    }
}
