package com.keitam.kayak.controller;

import com.keitam.kayak.model.KayakProduct;
import com.keitam.kayak.repository.KayakProductRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MainController {
    @FXML private AnchorPane root;

    private ObservableList<KayakProduct> kayakProducts = FXCollections.observableArrayList();
    @Autowired
    private KayakProductRepository productRepository;


    @FXML
    public void initialize(){
        listProduct();
        root.setOnMouseEntered(e -> {
            kayakProducts.forEach(p -> System.out.println(p.getpName()));

        });
    }

    @FXML
    private void listProduct(){
        productRepository.findAll().forEach(e -> kayakProducts.addAll(e));
    }
}
