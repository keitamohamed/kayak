package com.keitam.kayak.controller;

import com.keitam.kayak.repository.KayakProductRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KayakProductController {

    @FXML private AnchorPane root;

    @Autowired
    private KayakProductRepository productRepository;

    @FXML
    public void initialize(){

    }

    private List<com.keitam.kayak.model.KayakProduct> getProduct(){
        return productRepository.findAll();
    }
}
