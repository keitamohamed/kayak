package com.keitam.kayak.repository;

import com.keitam.kayak.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getKayakUserByUserNameAndPassword(@Param("UserName") String userName, @Param("Password") String password);
}
