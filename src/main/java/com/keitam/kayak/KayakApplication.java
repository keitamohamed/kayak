package com.keitam.kayak;

import com.keitam.kayak.util.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KayakApplication extends Application {
    private ConfigurableApplicationContext context;
    private Parent root;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(KayakApplication.class);
        PropertiesFile loader = context.getBean(PropertiesFile.class);
        root = loader.loadFXML("Main Index");
    }

    @Override
    public void start(Stage primaryStage) {
        StageManager.setStage(root, primaryStage, "Main Index").show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        context.close();
    }
}

