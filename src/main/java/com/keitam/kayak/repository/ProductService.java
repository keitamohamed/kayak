package com.keitam.kayak.repository;

import com.keitam.kayak.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    private ProductService(ProductRepository repository){
        this.productRepository = repository;
    }

    public ObservableList<Product> getAllProduct() {
        ObservableList<Product> allProduct = FXCollections.observableArrayList();
        productRepository.findAll().forEach(allProduct::addAll);
        return allProduct;
    }


}
