package com.keitam.kayak.repository;

import com.keitam.kayak.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AddressRepository extends JpaRepository<UserAddress, Long> {
}
