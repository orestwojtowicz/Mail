package com.damian.controller;

import com.damian.ModelAccess;
import com.damian.model.EmailMessageBean;
import com.damian.model.Singleton;
import com.damian.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.naming.OperationNotSupportedException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmailDetailsController implements Initializable {


/*
    public EmailDetailsController(ModelAccess modelAccess) {
        super(modelAccess);
    }
*/



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
