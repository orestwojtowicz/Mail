package com.damian.controller;

import com.damian.ModelAccess;
import com.damian.controller.services.CreateAndRegisterEmailAccountService;


import com.damian.controller.services.FolderUpdaterService;
import com.damian.controller.services.MessageRendererService;
import com.damian.model.EmailMessageBean;
import com.damian.model.GetResolveIcons;
import com.damian.model.Singleton;
import com.damian.model.folder.EmailFolderBean;

import com.damian.view.ViewFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {


/*
    public MainController(ModelAccess modelAccess) {
        super(modelAccess);
    }*/

    private Singleton singleton;

    private EmailFolderBean emailFolderBean = new EmailFolderBean("");

    private GetResolveIcons resolveIcons = new GetResolveIcons();

    private ModelAccess myModelAccess = new ModelAccess();



    @FXML
    private Button getEmails;

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
    private MessageRendererService messageRendererService;

    @FXML
    void ButtonClick(ActionEvent event) {

    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {


       messageRendererService = new MessageRendererService(messageRenderer.getEngine());


        ViewFactory viewFactory = ViewFactory.defaultFactory;

        FolderUpdaterService folderUpdaterService = new FolderUpdaterService(myModelAccess.getFoldersList());
        folderUpdaterService.start();




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



        EmailFolderBean<String> root = new EmailFolderBean<>("");
        emailFolderTreeView.setRoot(root);
        emailFolderTreeView.setShowRoot(false);


        CreateAndRegisterEmailAccountService CreateAndRegisterEmailAccountService1 = new CreateAndRegisterEmailAccountService("damianwojtowicz94@gmail.com", "Lapierre2010", root, myModelAccess);
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
              //  messageRenderer.getEngine().loadContent(messageBean.getHtmlContent());


                myModelAccess.setSelectedMessage(messageBean);

                messageRendererService.setMessageToRender(messageBean);

               singleton.setMessageBean(messageBean);

               // messageRendererService.restart(); // bo chcemy aby dzialalo za kazdym razem jak klikne a nie tylko raz jak klikne

                // bo zmieniam on Application thread wyglad i to sie r obi w runnable

                Platform.runLater(messageRendererService);



            }

        });


        emailTableView.setContextMenu(new ContextMenu(showDetails));

        showDetails.setOnAction(event -> {
            Scene scene = viewFactory.getEmailDetailScene();
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        });







































    /*    //-------------------TreeView Section START-----------------------

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


        emailFolderTreeView.setRoot(root);*/

        //----------------TreeView Section END------------------------




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
























