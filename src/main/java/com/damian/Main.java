package com.damian;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        ViewFactory viewFactory = new ViewFactory();

        Scene scene = viewFactory.getMainScene();

        primaryStage.setScene(scene);
        primaryStage.show();


    }


}
