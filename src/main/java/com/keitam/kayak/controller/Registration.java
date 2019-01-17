package com.keitam.kayak.controller;

import com.keitam.kayak.model.User;
import com.keitam.kayak.repository.UserService;
import com.keitam.kayak.util.Notification;
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
    private UserService userService;

    @FXML
    public void initialize(){
        clearFields.setOnAction(e -> clearField());
        register.setOnAction(e -> submitRegistration());
    }

    private void submitRegistration(){
        if (isFillOut() && isMatch()){
            User newUser = userService.getUserTextInput(firstName.getText(), lastName.getText(),
                    userName.getText(), password.getText());
            User user = userService.saveKayakUser(newUser, address.getText(), city.getText(), state.getText(),
                    zipCode.getText());
            if (user != null)
                Notification.message("User Added",
                        ("Hi " + user.getFirstName() + ", thank for registering. Your login name is " +
                                "" + user.getUserName() + " and password is " + user.getPassword()));
        }
    }

    private boolean isFillOut(){
        return !firstName.getText().isEmpty() && !lastName.getText().isEmpty()
                && !address.getText().isEmpty() && !city.getText().isEmpty()
                && !state.getText().isEmpty() && !zipCode.getText().isEmpty()
                && !userName.getText().isEmpty() && !password.getText().isEmpty()
                && !confirmPassword.getText().isEmpty();
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
    private Registration(StageManager manager, UserService uService){
        this.stageManager = manager;
        this.userService = uService;
    }
}
