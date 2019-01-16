package com.keitam.kayak.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class PropertiesFile {
    private final ApplicationContext context;

    @Autowired
    private PropertiesFile(ApplicationContext context) {
        this.context = context;
    }

    Parent loadSubFXML(String classTitle) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setLocation(getClass().getResource(getFXMLFile(classTitle)));
        return loader.load();
    }

    private String getFXMLFile(String classTitle) {
        String fxmlLocation = null;
        try {
            fxmlLocation = url("FXMLFile").getProperty(fxmlFile(classTitle));
        }catch (Exception e) {
            Notification.errorMessage(e.getMessage(), "Exception");
        }
        return fxmlLocation;
    }

    static String getStyleSheet(String classTitle) {
        String fxmlLocation = null;
        try {
            fxmlLocation = url("StyleSheet").getProperty(cssFileName(classTitle));
        }catch (Exception e) {
            Notification.errorMessage(e.getMessage(), "Exception");
        }
        return fxmlLocation;
    }

    private String fxmlFile(String value){
        switch (value) {
            case "Main Index":
                return "main_index";
            case "Kayak User Login":
                return "login_index";
            case "Register Index":
                return "register_index";
             default:
                 return "Employee";
        }
    }

    private static String cssFileName(String value) {
        switch (value) {
            case "Main Index":
                return "main_style";
            case "Kayak User Login":
                return "login_style";
            case "Register Index":
                return "register_style";
            default:
                return "Employee";
        }
    }

    private static Properties url(String propertiesFile) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = PropertiesFile.class.getResourceAsStream("/config/" + propertiesFile + ".properties");
        properties.load(inputStream);

        return properties;
    }

}
