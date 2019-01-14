package com.keitam.kayak;

import com.keitam.kayak.util.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KayakApplication extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = SpringApplication.run(KayakApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        StageManager stageManager = context.getBean(StageManager.class, primaryStage);
        stageManager.switchScene("Main Index");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        context.close();
    }
}

