package com.keitam.kayak.controller;

import com.keitam.kayak.exception.UserExceptionHandler;
import com.keitam.kayak.model.KayakUser;
import com.keitam.kayak.repository.KayakUserServiceImpl;
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
public class LoginController {
    @FXML private AnchorPane root;
    @FXML private Label incorrectLogin;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private Button login;

    @Lazy
    private StageManager stageManager;

    private List<KayakUser> users = FXCollections.observableArrayList();

    private KayakUserServiceImpl userService;

    @FXML
    public void initialize() {
        incorrectLogin.setVisible(false);
        login.setOnAction(e -> getKayakUser());
    }

    private void getKayakUser() {
        if (StringUtils.isNotBlank(userName.getText())
                && StringUtils.isNotBlank(password.getText())) {
            getUser();
            KayakUser user = userService.getUserByID(userName.getText(), password.getText());
            if (user == null) {
                throw new UserExceptionHandler(userName.getText() + " " + password.getText());
            }
            System.out.println(user.toString());
        }
    }

    private void getUser(){
        users.addAll(userService.findAllUser());
    }

    @Autowired
    public LoginController(StageManager manager, KayakUserServiceImpl service) {
        super();
        this.stageManager = manager;
        this.userService = service;
    }

}
