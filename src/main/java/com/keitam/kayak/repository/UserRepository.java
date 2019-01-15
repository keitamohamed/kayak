package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<KayakUser, Long> {
    KayakUser getKayakUserByUserNameAndPassword(@Param("UserName") String userName, @Param("Password") String password);

}
