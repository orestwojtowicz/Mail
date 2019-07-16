package com.damian;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

/**
 * class
 */

public class ViewFactory {

    public Scene getMainScene() {

        Parent root;


        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/main.fxml"));

        } catch (IOException e) {
            root = null;
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        return scene;


    }
    public Scene getEmailDetailScene() {

        Parent root;


        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/emailDetailsLayout.fxml"));

        } catch (IOException e) {
            root = null;
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        return scene;


    }

}
