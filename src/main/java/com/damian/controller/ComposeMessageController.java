package com.damian.controller;


import com.damian.controller.services.EmailSenderService;
import com.damian.imap.EmailConstants;
import com.damian.model.Singleton;
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

         private Singleton singleton;




    private ModelForMessageController modelAccess = new ModelForMessageController();



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

               modelAccess.getEmailAccountByName(senderChoice.getValue()),
               subjectField.getText(),
               recipentField.getText(),
               composeArea.getHtmlText(),
               attachments);


        emailSenderService.restart();
        emailSenderService.setOnSucceeded(e->{
            if(emailSenderService.getValue() == EmailConstants.MESSAGE_SENT_OK) {
                errorLabel.setText("message sent successfully");
            } else {
                errorLabel.setText("error while sending message");
            }
        });


    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {


        singleton = Singleton.getInstance();



                    senderChoice.setItems(modelAccess.getEmailAccountNames());
                   // senderChoice.setValue(modelAccess.getEmailAccountNames().get(0));



    }
}


























