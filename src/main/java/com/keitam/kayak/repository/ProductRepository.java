package com.keitam.kayak.repository;

import com.keitam.kayak.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProductRepository extends JpaRepository<Product, Long> {
}
