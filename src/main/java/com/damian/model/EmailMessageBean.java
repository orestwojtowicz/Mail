package com.damian.model;


import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailMessageBean {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;
    private boolean flag;
    private Message messageReference;

    private List<MimeBodyPart> attachmentsList = new ArrayList<>();
    private StringBuffer attachmentsNames = new StringBuffer();

    public static Map<String, Integer> formattedValues = new HashMap<>();

    public EmailMessageBean(String sender, String subject, int size, boolean flag, Message messageReference) {
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(formatSize(size));
        this.messageReference = messageReference;

    }

    public void addAttachment(MimeBodyPart mbp) throws MessagingException {
        attachmentsList.add(mbp);
        attachmentsNames.append(mbp.getFileName() + "; ");
    }

    public void clearAttachments() {
        attachmentsList.clear();
        attachmentsNames.setLength(0); // clear StringBuffer
    }

    public boolean hasAttachments() {
        return attachmentsList.size() > 0;
    }


    public List<MimeBodyPart> getAttachmentsList() {
        return attachmentsList;
    }

    public StringBuffer getAttachmentsNames() {
        return attachmentsNames;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Message getMessageReference() {
        return messageReference;
    }

    public String getSender() {
        return sender.get();
    }


    public String getSubject() {
        return subject.get();
    }



    public String getSize() {
        return size.get();
    }


    private String formatSize(int size) {

      String returnValue;

      if (size <= 0) {
          returnValue = "0";
      } else if (size < 1024) {
          returnValue = size + " B";
      } else if (size < 1048576) {
           returnValue = size / 1024 + " kB";
      } else {
          returnValue = size / 1048576 + " mB";
       }
      formattedValues.put(returnValue, size);
       return returnValue;
    }


}
/**
 * asdasd
 */