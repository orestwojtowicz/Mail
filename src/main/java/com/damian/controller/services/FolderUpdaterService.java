package com.damian.controller.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.mail.Folder;
import java.util.List;


public class FolderUpdaterService extends Service<Void> {


    private List<Folder> folderList;


    public FolderUpdaterService(List<Folder> folderList) {
        this.folderList = folderList;
    }



    @Override
    protected Task<Void> createTask() {

        return new Task<Void>() {
            @Override
            protected Void call() {

                // mamy liste folderow i chcemy aby co jakis czas, sprawdzac ilosc wiadomosci
                // infinite loop

                for (; ;) {
                    try {

                        Thread.sleep(10000);
                        System.out.println("UPDATE SERVICE RUNNING ");

                        for (Folder folder : folderList) {
                            if (folder.getType() != Folder.HOLDS_FOLDERS && folder.isOpen()) {
                                folder.getMessageCount();
                            }
                        }


                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }

            }


        };
    }
}