package com.keitam.kayak.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {

    private StageManager(){}

    public static Stage setStage(Parent root, Stage stage, String title) {
        stage.setTitle(title);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getScene().getStylesheets().add(StageManager.class.getResource(PropertiesFile.getStyleSheet(title)).toExternalForm());
        return stage;
    }

}
