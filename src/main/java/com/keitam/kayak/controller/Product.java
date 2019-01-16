package com.keitam.kayak.controller;


import com.keitam.kayak.repository.ProductService;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class Product {

    @FXML private AnchorPane root;

    @Autowired
    private ProductService productService;

    @FXML
    public void initialize(){

    }

    private List<com.keitam.kayak.model.Product> getProduct(){
        return productService.getAllProduct();
    }
}
