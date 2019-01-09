package com.keitam.kayak.controller;

import com.keitam.kayak.repository.KayakRepository;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController {
    @FXML private AnchorPane root;

    @Autowired
    private KayakRepository repository;

    @FXML
    private void initialize(){
        root.setOnMouseEntered(e -> {

        });
    }

}
