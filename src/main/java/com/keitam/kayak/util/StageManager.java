package com.keitam.kayak.util;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StageManager {
    private PropertiesFile propertiesFile;

    public StageManager(){}

    @Autowired
    private StageManager(PropertiesFile file){
        this.propertiesFile = file;
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
    private void setSubStage(Parent root, String title) throws IOException {
        if (propertiesFile == null)
            System.out.println("Print");
        Parent switchRoot = propertiesFile.loadFXML(title);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(root.getScene().getWindow());
        stage.setScene(new Scene(switchRoot));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
        stage.show();
    }

    public void switchScene(Parent root, String title){
        try {
            closeWindow(root);
            setSubStage(root, title);
        } catch (IOException e) {
            //Notification.errorMessage("IOException", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private static void closeWindow(Parent root){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
