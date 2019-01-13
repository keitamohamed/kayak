package com.keitam.kayak.util;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StageManager {
    private static PropertiesFile propertiesFile = new PropertiesFile();


    private StageManager(){

    }

    @FXML
    public static Stage setMainStage(Parent root, Stage stage, String title) {
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
        return stage;
    }

    @FXML
    private static void setSubStage(Stage stage, String title) throws IOException {
        AnchorPane root = propertiesFile.loadSubFXML(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
        stage.show();
    }

    public static void switchScene(AnchorPane root, String title){
        try {
            closeWindow(root);
            Stage stage = new Stage();
            setSubStage(stage, title);
        } catch (IOException e) {
            //Notification.errorMessage("IOException", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private static void closeWindow(AnchorPane root){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
