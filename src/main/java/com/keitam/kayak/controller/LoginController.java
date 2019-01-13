package com.keitam.kayak.controller;

import com.keitam.kayak.service.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    @FXML private AnchorPane root;
    @FXML private Label incorrectLogin;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private Button login;

    private UserService userService;

    @FXML
    public void initialize() {
        incorrectLogin.setVisible(false);
        login.setOnAction(e -> getKayakUser());
    }

    private void getKayakUser() {
        if (userService != null)
            System.out.println("Null point");
        userService.getAll().forEach(e -> System.out.println(e.getFirstName()));
    }

    public LoginController(){}


    @Autowired
    private LoginController(UserService service) {
        this.userService = service;
    }
}
