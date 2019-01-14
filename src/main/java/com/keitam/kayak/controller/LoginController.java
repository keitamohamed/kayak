package com.keitam.kayak.controller;

import com.keitam.kayak.model.KayakUser;
import com.keitam.kayak.repository.KayakUserServiceImpl;
import com.keitam.kayak.repository.UserRepository;
import com.keitam.kayak.repository.UserService;
import com.keitam.kayak.util.StageManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
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

    private UserService userService;

    @FXML
    public void initialize() {
        incorrectLogin.setVisible(false);
        login.setOnAction(e -> {
            getAllUser();
            if (users.isEmpty())
                System.out.println("Empty");
            users.forEach(System.out::println);
        });
    }

//    private void getKayakUser() {
//        if (userService != null)
//        userService.getAll().forEach(e -> System.out.println(e.getFirstName()));
//    }


    private void getAllUser(){
        users.addAll(userService.findAllUser());
    }

    @Autowired
    public LoginController(StageManager manager, UserService service) {
        super();
        this.stageManager = manager;
        this.userService = service;
    }

}
