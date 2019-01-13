package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;

import java.util.List;

public interface UserService {
    List<KayakUser> findAllUser();
    KayakUser getUser(Long id);
    KayakUser saveKayakUser(KayakUser user);
}
