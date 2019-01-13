package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<KayakUser, Long> {

//    @Query("select u.FirstName from UserTable where UserTable.UserID = :id")
//    KayakUser getByUserID(@Param("id") Long id);

}
