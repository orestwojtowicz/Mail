package com.damian.controller;


import com.damian.controller.services.EmailSenderService;
import com.damian.imap.EmailConstants;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ComposeMessageController implements Initializable {




    private List<File> attachments = new ArrayList<>();

   // private ModelForMessages modelAccess;



    @FXML
    private TextField recipentField;

    @FXML
    private HTMLEditor composeArea;

    @FXML
    private ChoiceBox<String> senderChoice;

    @FXML
    private TextField subjectField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label attachmentLabel;

    @FXML
    private JFXButton attachBtn;



    @FXML
    void attachBtnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser(); // open dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            attachments.add(selectedFile);
            attachmentLabel.setText(attachmentLabel.getText() + selectedFile.getName() + "; ");
        }



    }

    @FXML
    void sendBtnAction(ActionEvent event) {

        errorLabel.setText("");
       EmailSenderService emailSenderService = new EmailSenderService(

               ModelForMessages.modelForMessages.getEmailAccountByName(senderChoice.getValue()),
               subjectField.getText(),
               recipentField.getText(),
               composeArea.getHtmlText(),
               attachments);


        emailSenderService.restart();
        emailSenderService.setOnSucceeded(e->{
            if(emailSenderService.getValue() == EmailConstants.MESSAGE_SENT_OK) {
                errorLabel.setText("message sent successfully");
            } else {
                errorLabel.setText("message sent successfully error");
            }
        });


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {

                    senderChoice.setItems(ModelForMessages.modelForMessages.getEmailAccountNames());
                     senderChoice.setValue(ModelForMessages.modelForMessages.getEmailAccountNames().get(0));



    }
}



























