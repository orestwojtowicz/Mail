package com.damian.controller.services;

import com.damian.model.ModelForMessages;
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
    private ModelForMessages modelAccess;


    public FetchFoldersService(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean, ModelForMessages modelAccess) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
        this.modelAccess = modelAccess;

    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                if (emailAccountBean != null) {
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();

                    for (Folder folder : folders) {

                        modelAccess.addFolder(folder);

                        EmailFolderBean<String> item = new EmailFolderBean<>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);

                        item.setExpanded(true);

                        addMessageListenerToFolder(folder,item);

                        FetchMessagesOnFolderService fetchMessagesOnFolderService = new FetchMessagesOnFolderService(item, folder);
                        fetchMessagesOnFolderService.start();

                        System.out.println("added: " + folder.getName());
                        Folder[] subFolders = folder.list();
                        for (Folder subFolder : subFolders) {

                            if(!subFolder.getName().equals("All Mail")) {
                                modelAccess.addFolder(subFolder);
                                EmailFolderBean<String> subItem = new EmailFolderBean<>(subFolder.getName(), subFolder.getFullName());
                                item.getChildren().add(subItem);
                                System.out.println("added: " + subFolder.getName());
                                addMessageListenerToFolder(subFolder,subItem);
                                FetchMessagesOnFolderService fetchMessagesOnFolderServiceSubFolder = new FetchMessagesOnFolderService(subItem, subFolder);
                                fetchMessagesOnFolderServiceSubFolder.start();

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


}
