package com.keitam.kayak.repository;

import com.keitam.kayak.model.User;
import com.keitam.kayak.model.UserAddress;
import com.keitam.kayak.util.KayakUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired private UserService(UserRepository repository, AddressRepository userAddress) {
        this.userRepository = repository;
        this.addressRepository = userAddress;
    }

    public List<com.keitam.kayak.model.User> getUser(){
        return userRepository.findAll();
    }

    public User getUserByID(String userName, String password) {
       return userRepository.getKayakUserByUserNameAndPassword(userName, password);
    }

    public User saveKayakUser(User user, String address, String city, String state, String zipCode) {
        UserAddress userAddr = new UserAddress(user.getUserID(), address, city, state, Integer.parseInt(zipCode), user);

        addressRepository.save(userAddr);
        return user;
    }

    public User getUserTextInput(String fName, String lName, String userName, String password) {
        return createUser(fName, lName, userName, password);
    }

    private User createUser(String fName, String lName, String userName, String password) {
        Long userID = (long) KayakUtil.getGeneratedUserID();
        return new User(userID, fName, lName, userName, password, "Customer");
    }

}
