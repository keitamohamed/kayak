package com.keitam.kayak.controller;


import com.keitam.kayak.model.KayakProduct;
import com.keitam.kayak.repository.ProductService;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductController {

    @FXML private AnchorPane root;

    @Autowired
    private ProductService productService;

    @FXML
    public void initialize(){

    }

    private List<KayakProduct> getProduct(){
        return productService.getAllProduct();
    }
}
