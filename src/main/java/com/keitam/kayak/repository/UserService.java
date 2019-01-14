package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;

import java.util.List;

public interface UserService {
    List<KayakUser> findAllUser();
    KayakUser getUserLogin(String userName, String password);
    KayakUser getUser(Long id);
    KayakUser saveKayakUser(KayakUser user);
}
