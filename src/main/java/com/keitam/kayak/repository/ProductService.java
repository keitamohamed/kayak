package com.keitam.kayak.repository;

import com.keitam.kayak.model.KayakProduct;
import com.keitam.kayak.repository.ProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private ProductService(ProductRepository repository){
        this.productRepository = repository;
    }

    public ObservableList<KayakProduct> getAllProduct() {
        ObservableList<KayakProduct> allProduct = FXCollections.observableArrayList();
        productRepository.findAll().forEach(allProduct::addAll);
        return allProduct;
    }


}
