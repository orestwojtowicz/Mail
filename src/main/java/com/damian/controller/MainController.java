package com.damian.controller;


import com.damian.controller.services.AttachmentsHandleService;
import com.damian.controller.services.CreateAndRegisterEmailAccountService;


import com.damian.controller.services.FolderUpdaterService;
import com.damian.controller.services.MessageRendererService;
import com.damian.model.ModelForMessages;
import com.damian.model.messageBeanContainer.EmailMessageBean;
import com.damian.model.formatValues.FormatSizeValues;
import com.damian.model.resolveIcon.GetResolveIcons;
import com.damian.model.Singleton;
import com.damian.model.folder.EmailFolderBean;

import com.damian.view.ViewFactory;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    private static EmailFolderBean<String> root = new EmailFolderBean<>("");
    private Singleton singleton;
    private GetResolveIcons resolveIcons = new GetResolveIcons();
    private ModelForMessages modelForMessages = new ModelForMessages();



    @FXML
    private WebView messageRenderer;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectColumn;

    @FXML
    private TableColumn<EmailMessageBean, String> senderColumn;

    @FXML
    private TableColumn<EmailMessageBean, FormatSizeValues> sizeColumn;

    @FXML
    private TableView<EmailMessageBean> emailTableView;

    @FXML
    private TreeView<String> emailFolderTreeView;

    @FXML
    private MenuItem showDetails = new MenuItem("show details");

    @FXML
    private MessageRendererService messageRendererService;

    @FXML
    private TableColumn<EmailMessageBean, Date> dateCol;


    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label downloadLabel;

    @FXML
    private JFXButton downloadButton;

    @FXML
    private AttachmentsHandleService attachmentsHandleService;

    @FXML
    private JFXButton newMessage;

    @FXML
    private JFXButton addAccount;

    @FXML
    void addAccountOnAction(ActionEvent event) {
        Scene scene = ViewFactory.defaultFactory.getAddNewAccountScene();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }




   @FXML
   void DownloadAction(ActionEvent event) {
        EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
        if(messageBean != null && messageBean.hasAttachments()) {
            attachmentsHandleService.setMessage(messageBean);
            attachmentsHandleService.restart();
        }

    }

    // New Message Button
    //  ViewFactory viewFactory = ViewFactory.defaultFactory; using the same static instance
    @FXML
    void ButtonClick(ActionEvent event) {
        Scene scene = ViewFactory.defaultFactory.getComposeMessageScene();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        System.out.println("STARTED");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

       // disabling buttons, when folders are not loaded
        disableButtonHelperMethod(newMessage, downloadButton);

       /**
        * adding css styles
        * */

        downloadButton.getStyleClass().add("basicButtons");
        newMessage.getStyleClass().add("basicButtons");
        addAccount.getStyleClass().add("basicButtons");



        progressBar.setVisible(false);


        messageRendererService = new MessageRendererService(messageRenderer.getEngine());

        attachmentsHandleService = new AttachmentsHandleService(progressBar, downloadLabel);

        progressBar.progressProperty().bind(attachmentsHandleService.progressProperty());






        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService1 = new CreateAndRegisterEmailAccountService("", "", root, ModelForMessages.modelForMessages);
        createAndRegisterEmailAccountService1.start();





        ViewFactory viewFactory = ViewFactory.defaultFactory;

       FolderUpdaterService folderUpdaterService = new FolderUpdaterService(modelForMessages.getFoldersList());
      folderUpdaterService.start();

        singleton = Singleton.getInstance();

        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));





            emailFolderTreeView.setRoot(root);
            emailFolderTreeView.setShowRoot(false);











        emailFolderTreeView.setOnMouseClicked(event -> {
            // TreeItem<String> item = emailFolderTreeView.getSelectionModel().getSelectedItem();
            EmailFolderBean<String> item = (EmailFolderBean<String>) emailFolderTreeView.getSelectionModel().getSelectedItem();
            if(item != null) {
                emailTableView.setItems(item.getData());
                resolveIcons.setSelectedFolder(item);
                // clear the selected message
                resolveIcons.setSelectedFolder(null);

            }
        });


        emailTableView.setOnMouseClicked(event -> {
            EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
            if(messageBean != null) {
                modelForMessages.setSelectedMessage(messageBean);
                messageRendererService.setMessageToRender(messageBean);
                singleton.setMessageBean(messageBean);
                messageRendererService.restart(); // bo chcemy aby dzialalo za kazdym razem jak klikne a nie tylko raz jak klikne

                // bo zmieniam on Application thread wyglad i to sie r obi w runnable

                // Platform.runLater(messageRendererService);

            }

        });


        emailTableView.setContextMenu(new ContextMenu(showDetails));

        showDetails.setOnAction(event -> {
            Scene scene = viewFactory.getEmailDetailScene();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });

    }

    private void disableButtonHelperMethod(JFXButton newMessage, JFXButton downloadButton) {

        newMessage.setDisable(true);
        downloadButton.setDisable(true);
        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(5),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                newMessage.setDisable(false);
                                downloadButton.setDisable(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

}


























