package com.damian.controller.services;

import com.damian.ModelAccess;
import com.damian.imap.EmailAccountBean;
import com.damian.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.event.MessageCountAdapter;
import javax.mail.event.MessageCountEvent;

public class FetchFoldersService extends Service<Void> {

    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;

    private ModelAccess modelAccess;

   private static int NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE = 0;


    public FetchFoldersService(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean, ModelAccess modelAccess) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
        this.modelAccess = modelAccess;

        this.setOnSucceeded(e->{
            NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE--;
        });

    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE++;
                if (emailAccountBean != null) {
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();

                    for (Folder folder : folders) {

                        modelAccess.addFolder(folder);

                        EmailFolderBean<String> item = new EmailFolderBean<>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);

                        item.setExpanded(true);

                        addMessageListenerToFolder(folder,item);


                        FetchMessagesService fetchMessagesService = new FetchMessagesService(item, folder);
                        fetchMessagesService.start();

                        System.out.println("added: " + folder.getName());
                        Folder[] subFolders = folder.list();
                        for (Folder subFolder : subFolders) {

                            modelAccess.addFolder(subFolder);

                            EmailFolderBean<String> subItem = new EmailFolderBean<>(subFolder.getName(), subFolder.getFullName());

                            if(!subFolder.getName().contains("Oznaczone gwiazdką")) {
                                item.getChildren().add(subItem);
                                System.out.println("added: " + subFolder.getName());

                                addMessageListenerToFolder(subFolder,subItem);

                                FetchMessagesService fetchMessagesServiceSubFolder = new FetchMessagesService(subItem, subFolder);
                                fetchMessagesServiceSubFolder.start();
                            }

                        }
                    }
                }
                return null;
            }

        };
    }



    private void addMessageListenerToFolder(Folder folder, EmailFolderBean<String> item) {

        folder.addMessageCountListener(new MessageCountAdapter() {
            @Override
            public void messagesAdded(MessageCountEvent e) {
                for(int i = 0; i < e.getMessages().length; i++) {
                    try {
                        Message currentMessage = folder.getMessage(folder.getMessageCount() - i);
                        item.addEmail(0, currentMessage);
                    } catch (MessagingException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }




public static boolean noServicesActive() {
        return NUMBER_OF_FETCHFOLDERSSERVICES_ACTIVE == 0;
}

// ROZNE STATE OF THREADS







}