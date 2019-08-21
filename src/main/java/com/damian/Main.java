package com.damian;

import com.damian.view.ViewFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

        primaryStage.getIcons().add(
                new Image(
                        Main.class.getResourceAsStream("/img/mainIcon.png")
                )
        );

        primaryStage.setTitle("Email Client");


        primaryStage.show();


    }
}

