package com.damian.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GetEmailsData {

    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();

    public ObservableList<EmailMessageBean> getData() {
        return data;
    }
}
