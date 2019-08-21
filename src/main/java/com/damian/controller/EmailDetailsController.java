package com.damian.controller;

import com.damian.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsController implements Initializable {






    private Singleton singleton;


    @FXML
    private Label subjectLabel;

    @FXML
    private Label senderLabel;



/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        EmailMessageBean selectedMessage = getModelAccess().getSelectedMessage();


        subjectLabel.setText("Subject: " + selectedMessage.getSubject());
        senderLabel.setText("Sender: " + selectedMessage.getSender());

        //webView.getEngine().loadContent(selectedMessage.getContent());

    }
*/


  @Override
    public void initialize(URL location, ResourceBundle resources) {

      singleton = Singleton.getInstance();

      System.out.println("EmailDetailController initialized");

      subjectLabel.setText(singleton.getMessageBean().getSubject());
      senderLabel.setText(singleton.getMessageBean().getSender());

    }
}
