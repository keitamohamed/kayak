package com.keitam.kayak.util;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Notification {

    private Notification(){}

    static void errorMessage(String message, String title) {
        Notifications.create()
                .darkStyle()
                .title(title)
                .text(message)
                .position(Pos.CENTER)
                .hideAfter(Duration.minutes(1))
                .show();
    }

    public static void message(String title, String message) {
        Notifications.create()
                .darkStyle()
                .title(title)
                .text(message)
                .position(Pos.CENTER)
                .hideAfter(Duration.minutes(1))
                .show();
    }

    public static void operationNotPerform(String title, String message){
        Notifications.create()
                .darkStyle()
                .title(title)
                .text(message)
                .position(Pos.CENTER)
                .hideAfter(Duration.minutes(1))
                .show();
    }
}
