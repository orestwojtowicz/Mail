package com.damian.controller.services;

import com.damian.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;


public class FetchMessagesService extends Service<Void> {

    private EmailFolderBean<String> emailFolderBean;
    private Folder folder;


    public FetchMessagesService(EmailFolderBean<String> emailFolderBean, Folder folder) {
        this.emailFolderBean = emailFolderBean;
        this.folder = folder;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                if(folder.getType() != Folder.HOLDS_FOLDERS) {

                    folder.open(Folder.READ_WRITE);

                }

                int folderSize = folder.getMessageCount();
                for(int i = folderSize; i > 0; i--) {
                    Message currentMessage = folder.getMessage(i);
                    emailFolderBean.addEmail(-1, currentMessage);
                }

                return null;
            }
        };
    }
}



// Folder.HOLDS_FOLDERS
// watek demoniczy


// Message Listener, zeby aplikacja sie sama odswiezala po jakims czasie





