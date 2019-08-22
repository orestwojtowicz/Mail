package com.damian.controller;


import com.damian.controller.services.AttachmentsHandleService;
import com.damian.controller.services.CreateAndRegisterEmailAccountService;


import com.damian.controller.services.FolderUpdaterService;
import com.damian.controller.services.MessageRendererService;
import com.damian.model.messageBeanContainer.EmailMessageBean;
import com.damian.model.formatValues.FormatSizeValues;
import com.damian.model.resolveIcon.GetResolveIcons;
import com.damian.model.Singleton;
import com.damian.model.folder.EmailFolderBean;

import com.damian.view.ViewFactory;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainController implements Initializable {




    private Singleton singleton;

    private EmailFolderBean emailFolderBean = new EmailFolderBean("");

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



    // getEmails newMessage
    //  ViewFactory viewFactory = ViewFactory.defaultFactory; using the same static instance
    @FXML
    void ButtonClick(ActionEvent event) {

        Scene scene = ViewFactory.defaultFactory.getComposeMessageScene();
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label downloadLabel;

    @FXML
    private JFXButton downloadButton;

    @FXML
    private AttachmentsHandleService attachmentsHandleService;

   @FXML
   void DownloadAction(ActionEvent event) {
        EmailMessageBean messageBean = emailTableView.getSelectionModel().getSelectedItem();
        if(messageBean != null && messageBean.hasAttachments()) {
            attachmentsHandleService.setMessage(messageBean);
            attachmentsHandleService.restart();
        }
   }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


       /**
        * adding css styles
        * */

        downloadButton.getStyleClass().add("addBobOk");



        progressBar.setVisible(false);


        messageRendererService = new MessageRendererService(messageRenderer.getEngine());

        attachmentsHandleService = new AttachmentsHandleService(progressBar, downloadLabel);

        progressBar.progressProperty().bind(attachmentsHandleService.progressProperty());


        ViewFactory viewFactory = ViewFactory.defaultFactory;

        FolderUpdaterService folderUpdaterService = new FolderUpdaterService(modelForMessages.getFoldersList());
        folderUpdaterService.start();



        singleton = Singleton.getInstance();

        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));




        EmailFolderBean<String> root = new EmailFolderBean<>("");
        emailFolderTreeView.setRoot(root);
        emailFolderTreeView.setShowRoot(false);


        CreateAndRegisterEmailAccountService CreateAndRegisterEmailAccountService1 = new CreateAndRegisterEmailAccountService("krztuszenie@gmail.com", "", root, ModelForMessages.modelForMessages);
        CreateAndRegisterEmailAccountService1.start();





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

}




// java.lang.IllegalStateException: Not on FX application thread; currentThread = Thread-26
// thread jesto dpowiedzialne za renderowanie wygladu i uzywamy teraz innego thread aby zmienic wyglad
// jesli chce zmienic wyglad w innym thread
// Zmiany w GUI musisz odpalać w Dispatch Thread.
/*

Platform.runLater(new Runnable() {
@Override
public void run() {
        // if you change the UI, do it here !
        }
        });

*/

// button for getting all emails
/*
        getEmails.setOnAction((event -> {
            Service<Void> emailService = new Service<Void>(){
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>(){
                        @Override
                        protected Void call() throws Exception {
                            ObservableList<EmailMessageBean> data = ema

                            final EmailAccountBean emailAccountBean = new EmailAccountBean("damianwojtowicz94@gmail.com", "");
                            emailAccountBean.addEmailsToData(data);

                            return null;
                        }

                    };
                }

            };
            emailService.start();

        }));
*/
























