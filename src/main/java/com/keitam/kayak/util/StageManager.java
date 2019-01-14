package com.keitam.kayak.util;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class StageManager {
    private PropertiesFile propertiesFile;


    public StageManager(PropertiesFile file){
        this.propertiesFile = file;
    }

    private void setStage(String title) throws IOException {
        Parent switchRoot = propertiesFile.loadSubFXML(title);
        Objects.requireNonNull(switchRoot, "FXML root node cannot be null");
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(switchRoot));
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
        stage.show();
    }

    public void switchScene(String title){
        try {
            setStage(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow(Parent root){
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

}
