package com.keitam.kayak.controller;

import com.keitam.kayak.repository.KayakUserServiceImpl;
import com.keitam.kayak.util.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Controller
public class Registration {

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField address;
    @FXML private TextField city;
    @FXML private TextField state;
    @FXML private TextField zipCode;
    @FXML private TextField userName;
    @FXML private PasswordField password;
    @FXML private PasswordField confirmPassword;
    @FXML private Button clearFields;
    @FXML private Button register;


    @Lazy
    private StageManager stageManager;
    private KayakUserServiceImpl userService;

    @FXML
    public void initialize(){
        clearFields.setOnAction(e -> clearField());
        register.setOnAction(e -> submitRegistration());
    }

    private void submitRegistration(){
        if (isMatch()){

        }

    }

    private boolean isMatch(){
        return password.getText().equals(confirmPassword.getText());
    }

    private void clearField(){
        firstName.setText("");
        lastName.setText("");
        address.setText("");
        city.setText("");
        state.setText("");
        zipCode.setText("");
        userName.setText("");
        password.setText("");
        confirmPassword.setText("");
    }

    @Autowired
    private Registration(StageManager manager, KayakUserServiceImpl uService){
        this.stageManager = manager;
        this.userService = uService;
    }
}
