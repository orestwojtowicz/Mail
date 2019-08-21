package com.damian.controller;

import com.damian.imap.EmailAccountBean;
import com.damian.model.EmailMessageBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.Folder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelForMessageController {

    private Map<String, EmailAccountBean> emailAccounts = new HashMap<>();
    private ObservableList<String> emailAccountsNames = FXCollections.observableArrayList();



    public ObservableList<String> getEmailAccountNames(){
        return emailAccountsNames;
    }

    public EmailAccountBean getEmailAccountByName(String name){
        return emailAccounts.get(name);
    }

    public void addAccount(EmailAccountBean account){
        emailAccounts.put(account.getEmailAddress(), account);
        emailAccountsNames.add(account.getEmailAddress());
    }



    private EmailMessageBean selectedMessage;



    public void setSelectedMessage(EmailMessageBean selectedMessage) {
        this.selectedMessage = selectedMessage;
    }



    private List<Folder> foldersList = new ArrayList<>();

    public List<Folder> getFoldersList(){
        return  foldersList;
    }

    public void addFolder(Folder folder){
        foldersList.add(folder);
    }


}
