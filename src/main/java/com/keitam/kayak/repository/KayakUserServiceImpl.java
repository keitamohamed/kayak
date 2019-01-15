package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;
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
    public List<KayakUser> findAllUser() {
        return userRepository.findAll();
    }

    public KayakUser getUserByID(String userName, String password) {
       return userRepository.getKayakUserByUserNameAndPassword(userName, password);
    }

    @Override
    public KayakUser getUserLogin(String userName, String password) {
        return null;
    }

    @Override
    public KayakUser getUser(Long id) {
        return null;
    }

    @Override
    public KayakUser saveKayakUser(KayakUser user) {
        return null;
    }
}
