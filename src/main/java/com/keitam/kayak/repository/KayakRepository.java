package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KayakRepository extends JpaRepository<KayakUser, Long> {
}
