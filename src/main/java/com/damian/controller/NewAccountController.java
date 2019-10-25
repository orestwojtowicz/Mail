/*
package com.damian.controller;


import com.damian.controller.services.AttachmentsHandleService;
import com.damian.controller.services.CreateAndRegisterEmailAccountService;
import com.damian.controller.services.FolderUpdaterService;
import com.damian.controller.services.MessageRendererService;
import com.damian.imap.EmailAccountBean;
import com.damian.model.ModelForMessages;
import com.damian.model.folder.EmailFolderBean;
import com.damian.view.ViewFactory;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountController implements Initializable {


    private static EmailFolderBean<String> root = new EmailFolderBean<>("");
    public static NewAccountController newAccountController = new NewAccountController();



    @FXML
    JFXTextField email;

    @FXML
    JFXPasswordField password;

    @FXML
    Button submit;



    @FXML
   Integer addAccount(ActionEvent event) {



        CreateAndRegisterEmailAccountService createAndRegisterEmailAccountService1 =
                new CreateAndRegisterEmailAccountService(email.getText(), password.getText(),
                        root, ModelForMessages.modelForMessages);
        createAndRegisterEmailAccountService1.start();

        System.out.println("EMAIL " + email.getText() + " PASSWORD " + password.getText());

        FolderUpdaterService folderUpdaterService = new FolderUpdaterService(ModelForMessages.modelForMessages.getFoldersList());
        folderUpdaterService.start();


        return 1;
    }


    private void accountMethod() {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        submit.getStyleClass().add("basicButtons");


    }

}
*/
