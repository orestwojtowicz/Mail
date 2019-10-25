package com.damian.model.messageBeanContainer;


import com.damian.model.formatValues.FormatSizeValues;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import java.util.*;



/**
 * asdasd
 */


public class EmailMessageBean {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleObjectProperty<FormatSizeValues> size;
    private Message messageReference;
    private SimpleObjectProperty<Date> date;
    private List<MimeBodyPart> attachmentsList = new ArrayList<>();
    private StringBuffer attachmentsNames = new StringBuffer();





    public EmailMessageBean(String sender, String subject, int size, Message messageReference, Date date) {
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleObjectProperty<FormatSizeValues>(new FormatSizeValues(size));
        this.messageReference = messageReference;
        this.date = new SimpleObjectProperty<>(date);

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


    public Date getDate() {
        return date.get();
    }

    public SimpleObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public FormatSizeValues getSize() {
        return size.get();
    }

    public SimpleObjectProperty<FormatSizeValues> sizeProperty() {
        return size;
    }

    public void setSize(FormatSizeValues size) {
        this.size.set(size);
    }

    public List<MimeBodyPart> getAttachmentsList() {
        return attachmentsList;
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


}
