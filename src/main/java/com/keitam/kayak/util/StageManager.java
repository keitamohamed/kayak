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

    public static void setSubStage(String title) throws IOException {
        Parent root = propertiesFile.loadSubFXML(title);
        Stage stage = new Stage();
        stage.setScene(root.getScene());
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
        //stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
    }

    @FXML
    public static void closeWindow(AnchorPane root){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
