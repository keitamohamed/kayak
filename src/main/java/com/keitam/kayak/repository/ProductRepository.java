package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<KayakProduct, Long> {
}
