package com.damian.controller.services;

import com.damian.imap.EmailAccountBean;
import com.damian.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;

public class FetchFoldersService extends Service<Void> {

    private EmailFolderBean<String> foldersRoot;
    private EmailAccountBean emailAccountBean;


    public FetchFoldersService(EmailFolderBean<String> foldersRoot, EmailAccountBean emailAccountBean) {
        this.foldersRoot = foldersRoot;
        this.emailAccountBean = emailAccountBean;
    }


    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (emailAccountBean != null) {
                    Folder[] folders = emailAccountBean.getStore().getDefaultFolder().list();

                    for (Folder folder : folders) {
                        EmailFolderBean<String> item = new EmailFolderBean<>(folder.getName(), folder.getFullName());
                        foldersRoot.getChildren().add(item);
                        item.setExpanded(true);
                        System.out.println("added: " + folder.getName());
                        Folder[] subFolders = folder.list();
                        for (Folder subFolder : subFolders) {
                            EmailFolderBean<String> subItem = new EmailFolderBean<>(subFolder.getName(), subFolder.getFullName());
                            item.getChildren().add(subItem);
                            System.out.println("added: " + subFolder.getName());
                        }
                    }
                }
                return null;
            }

        };
    }
}