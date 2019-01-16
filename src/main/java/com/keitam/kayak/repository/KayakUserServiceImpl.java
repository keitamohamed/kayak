package com.keitam.kayak.repository;

import com.keitam.kayak.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KayakUserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired private KayakUserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public User getUserByID(String userName, String password) {
       return userRepository.getKayakUserByUserNameAndPassword(userName, password);
    }

    @Override
    public User getUserLogin(String userName, String password) {
        return null;
    }

    @Override
    public User getUser(Long id) {
        return null;
    }

    @Override
    public User saveKayakUser(User user) {
        return null;
    }
}
