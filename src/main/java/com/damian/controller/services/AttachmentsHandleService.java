package com.damian.controller.services;

import com.damian.model.messageBeanContainer.EmailMessageBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;


public class AttachmentsHandleService extends Service<Void> {


    private EmailMessageBean message;
    private String LOCATION_OF_DOWNLOADS = System.getProperty("user.home") + "/Downloads/";
    private ProgressBar progressBar;
    private Label label;




    public AttachmentsHandleService(ProgressBar progressBar, Label label) {

        this.progressBar = progressBar;
        this.label = label;
        this.setOnRunning(e->showVisuals(true));
        this.setOnSucceeded(e->showVisuals(false));

    }

    // always call before starting
    public void setMessage(EmailMessageBean message) {
        this.message = message;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>(){
            @Override
            protected Void call() {

                for(MimeBodyPart mpb: message.getAttachmentsList()) {
                    try {
                        updateProgress(message.getAttachmentsList().indexOf(mpb),
                                  message.getAttachmentsList().size());
                        mpb.saveFile(LOCATION_OF_DOWNLOADS + mpb.getFileName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }

        };
    }


    private void showVisuals(boolean show) {
        progressBar.setVisible(show);
        label.setVisible(show);
    }

}






















// WARNING: Can not retrieve property 'date' in PropertyValueFactory: javafx.scene.control.cell.PropertyValueFactory@c166ed with provided class type: class com.damian.model.messageBeanContainer.EmailMessageBean







