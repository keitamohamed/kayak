package com.keitam.kayak.repository;

import com.keitam.kayak.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUser();
    User getUserLogin(String userName, String password);
    User getUser(Long id);
    User saveKayakUser(User user);
}
