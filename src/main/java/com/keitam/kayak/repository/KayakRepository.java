package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KayakRepository extends JpaRepository<KayakUser, Long> {
    @Override
    List<KayakUser> findAll();
    KayakUser findAllByUserID(Long userID);
}
