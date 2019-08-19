package com.damian.view;

import com.damian.controller.EmailDetailsController;
import com.damian.controller.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class ViewFactory {


    public static ViewFactory defaultFactory = new ViewFactory();


    private MainController mainScene;
    private EmailDetailsController detailsController;
    private final String CSS_FILE = "/css/style.css";
    private final String MAIN_FXML = "fxml/main.fxml";
    private final String DETAILS_FXML = "fxml/emailDetailsLayout.fxml";





    public Scene getMainScene() {
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(MAIN_FXML));

        } catch (IOException e) {
            root = null;
            e.printStackTrace();
        }
        return sceneInitializerHelperMethod(root, CSS_FILE);
    }

    public Scene getEmailDetailScene() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(DETAILS_FXML));

        } catch (IOException e) {
            root = null;
            e.printStackTrace();
        }

      return sceneInitializerHelperMethod(root, CSS_FILE);
    }

    private Scene sceneInitializerHelperMethod(Parent root, String cssFile) {

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource(cssFile).toExternalForm());
        return scene;
    }










}



























