package com.damian.model.folder;

import com.damian.model.messageBeanContainer.EmailMessageBean;
import com.damian.view.IconResolver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;


import javax.mail.Message;
import javax.mail.MessagingException;

public class EmailFolderBean<T> extends TreeItem<String> {

    private String name;
    private String completeName;
    private ObservableList<EmailMessageBean> data = FXCollections.observableArrayList();


    public EmailFolderBean(String value) {
        super(value, IconResolver.iconResolver.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
        this.setExpanded(true);
    }


    public EmailFolderBean(String value, String completeName) {
        super(value, IconResolver.iconResolver.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
       }


       public void addEmail(int position, Message message) {
           try {
              // boolean isRead = message.getFlags().contains(Flags.Flag.SEEN);
               EmailMessageBean emailMessageBean = new EmailMessageBean(message.getSubject(),
                       message.getFrom()[0].toString(),
                       message.getSize(),
                       message,
                       message.getSentDate());

               if(position < 0) {
                   data.add(emailMessageBean);
               } else {
                   data.add(position, emailMessageBean);
               }

            // add message to list
             data.add(emailMessageBean);


           } catch (MessagingException e) {
               e.printStackTrace();
           }
       }

       // return Observable containing messages
     public ObservableList<EmailMessageBean> getData() {
        return data;
    }


}

















