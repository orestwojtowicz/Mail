package com.damian;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.mail.NoSuchProviderException;

public class Test {

    public static void main(String[] args) {
        final EmailAccountBean emailAccountBean;
        try {
            emailAccountBean = new EmailAccountBean("***", "***");

            ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();
            emailAccountBean.addEmailstoData(data);
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }


    }

}
