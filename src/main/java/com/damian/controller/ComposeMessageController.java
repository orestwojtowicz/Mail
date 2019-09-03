package com.damian.controller;


import com.damian.controller.services.EmailSenderService;
import com.damian.imap.EmailConstants;
import com.damian.model.ModelForMessages;
import com.jfoenix.controls.JFXButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.util.Duration;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ComposeMessageController implements Initializable {




    private List<File> attachments = new ArrayList<>();

   // private ModelForMessages modelAccess;

    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";



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
    private JFXButton sentBtn;

    @FXML
    private JFXButton attachBtn;

    @FXML
    void attachBtnAction(ActionEvent event) {

        FileChooser fileChooser = new FileChooser(); // open dialog
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
            attachments.add(selectedFile);
            attachmentLabel.setText("");
            attachmentLabel.setText(attachmentLabel.getText() + selectedFile.getName() + ", ");
        }

    }







    @FXML
    void sendBtnAction(ActionEvent event) {

        errorLabel.setText("Sending message....");

       EmailSenderService emailSenderService = new EmailSenderService(

               ModelForMessages.modelForMessages.getEmailAccountByName(senderChoice.getValue()),
               subjectField.getText(),
               recipentField.getText(),
               composeArea.getHtmlText(),
               attachments);

        emailSenderService.restart();
        emailSenderService.setOnSucceeded(e->{
            errorLabel.setStyle("-fx-background-color: none");
            if(emailSenderService.getValue() == EmailConstants.MESSAGE_SENT_OK) {
                errorLabel.setText("message sent successfully");
                errorLabel.setStyle("-fx-background-color: green");
            } else {
                errorLabel.setText("error while sending message");

                final Timeline animation = new Timeline(
                        new KeyFrame(Duration.seconds(0.5),
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                       errorLabel.setStyle("-fx-background-color: red;");
                                    }
                                }));
                animation.setCycleCount(10);
                animation.play();

                errorLabel.setText("");

            }


            Alert alert = new Alert(Alert.AlertType.WARNING);
            if(recipentField.getLength() < 3) {
                alert.setTitle("Wrong email address");
                alert.setContentText("Your email address is incorrect");
                errorLabel.setText("");
                alert.showAndWait();
            }

        });


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

                 sentBtn.getStyleClass().add("basicButtons");
                 attachBtn.getStyleClass().add("basicbuttons");

                 senderChoice.setItems(ModelForMessages.modelForMessages.getEmailAccountNames());
                 senderChoice.setValue(ModelForMessages.modelForMessages.getEmailAccountNames().get(0));

    }
}





// https://code.makery.ch/blog/javafx-dialogs-official/





















