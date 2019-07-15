package com.damian;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private static final String myAddressRoot = "damianwojtowicz94@gmail.com";
    private SampleData sampleData = new SampleData();
    private Singleton singleton;

    @FXML
    private Button button1;

    @FXML
    private WebView messageRenderer;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectColumn;

    @FXML
    private TableColumn<EmailMessageBean, String> senderColumn;

    @FXML
    private TableColumn<EmailMessageBean, String> sizeColumn;

    @FXML
    private TableView<EmailMessageBean> emailTableView;

    @FXML
    private TreeView<String> emailFolderTreeView;

    @FXML
    private MenuItem showDetails = new MenuItem("show details");


    @FXML
    void ButtonClick(ActionEvent event) {
        System.out.println("Button clicked");
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        singleton = Singleton.getInstance();





        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));




        sizeColumn.setComparator(new Comparator<>() {

            Integer int1, int2;

            @Override
            public int compare(String s, String t1) {
                int1 = EmailMessageBean.formattedValues.get(s);
                int2 = EmailMessageBean.formattedValues.get(t1);
                return int1.compareTo(int2);
            }
        });


        button1.setOnAction((event -> System.out.println("Clicked")));





        //-------------------TreeView Section START-----------------------




   final Node emailImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/mail-open-flat.png")));
        ((ImageView) emailImage).setFitHeight(25);
        ((ImageView) emailImage).setFitWidth(25);


        final  Node inboxImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/inbox-flat.png")));
        ((ImageView) inboxImage).setFitHeight(25);
        ((ImageView) inboxImage).setFitWidth(25);



        final  Node sentImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/sent.png")));
        ((ImageView) sentImage).setFitHeight(25);
        ((ImageView) sentImage).setFitWidth(25);


        final Node folderImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/folder.png")));
        ((ImageView) folderImage).setFitHeight(25);
        ((ImageView) folderImage).setFitWidth(25);

        final  Node folderImage1 = new ImageView(
                new Image(getClass().getResourceAsStream("/img/folder.png")));
        ((ImageView) folderImage1).setFitHeight(25);
        ((ImageView) folderImage1).setFitWidth(25);



        final  Node spamImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/spam.png")));
        ((ImageView) spamImage).setFitHeight(25);
        ((ImageView) spamImage).setFitWidth(25);


        final  Node trashImage = new ImageView(
                new Image(getClass().getResourceAsStream("/img/trash.png")));
        ((ImageView) trashImage).setFitHeight(25);
        ((ImageView) trashImage).setFitWidth(25);


        TreeItem<String> root = new TreeItem<>(myAddressRoot,emailImage);
        root.setExpanded(true);




        TreeItem<String> inbox = new TreeItem<>("inbox", inboxImage);
        TreeItem<String> sent = new TreeItem<>("sent",sentImage);
        TreeItem<String> spam = new TreeItem<>("spam", spamImage);
        TreeItem<String> folder = new TreeItem<>("folder", trashImage);


        // root tree structure

        root.getChildren().addAll(inbox, sent,folder, spam);

        // structure for children of inbox, folder, sent, spam

        TreeItem<String> subSent1 = new TreeItem<>("subSent1", folderImage);
        TreeItem<String> subSent2 = new TreeItem<>("subSent1", folderImage1);


        sent.setExpanded(true);
        sent.getChildren().addAll(subSent1,subSent2);


        emailFolderTreeView.setRoot(root);

        //----------------TreeView Section END------------------------




        emailFolderTreeView.setOnMouseClicked(event -> {
            TreeItem<String> item = emailFolderTreeView.getSelectionModel().getSelectedItem();
            if(item != null) {
                emailTableView.setItems(sampleData.emailFolder.get(item.getValue()));
            }
        });



        emailTableView.setOnMouseClicked(event -> {
        EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
        if(messageBean != null) {
            messageRenderer.getEngine().loadContent(messageBean.getHtmlContent());
            singleton.setMessageBean(messageBean);
        }


    });


        emailTableView.setContextMenu(new ContextMenu(showDetails));

        showDetails.setOnAction(event -> {

            Parent parent = null;
            Stage primaryStage = new Stage();

            try {

                parent = FXMLLoader.load(getClass().getClassLoader().getResource("emailDetailsLayout.fxml"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(parent);
            scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();


        });

    }

}












































