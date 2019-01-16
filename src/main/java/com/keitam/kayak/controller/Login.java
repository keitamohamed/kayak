package com.keitam.kayak.controller;

import com.keitam.kayak.exception.UserExceptionHandler;
import com.keitam.kayak.model.User;
import com.keitam.kayak.repository.UserService;
import com.keitam.kayak.util.StageManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
@Controller
public class Login {
    @FXML private AnchorPane root;
    @FXML private Label incorrectLogin;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private Button login;
    @FXML private Button register;

    @Lazy
    private StageManager stageManager;

    private List<User> users = FXCollections.observableArrayList();

    private UserService userService;

    @FXML
    public void initialize() {
        incorrectLogin.setVisible(false);
        login.setOnAction(e -> getKayakUser());
        switchScene();
    }

    private void getKayakUser() {
        if (StringUtils.isNotBlank(userName.getText())
                && StringUtils.isNotBlank(password.getText())) {
            getUser();
            User user = userService.getUserByID(userName.getText(), password.getText());
            if (user == null) {
                throw new UserExceptionHandler(userName.getText() + " " + password.getText());
            }
        }
    }

    @FXML
    private void switchScene() {
        register.setOnAction(e -> {
            stageManager.closeWindow(root);
            stageManager.switchScene("Register Index");
        });
    }

    private void getUser(){
        users.addAll(userService.getUser());
    }

    @Autowired
    public Login(StageManager manager, UserService service) {
        this.stageManager = manager;
        this.userService = service;
    }

}
