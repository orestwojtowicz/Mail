package com.damian.controller.services;


import com.damian.imap.EmailAccountBean;
import com.damian.imap.EmailConstants;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

// sql data source

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EmailSenderService extends Service<Integer> {

    private int result;
    private EmailConstants emailConstants;

    private EmailAccountBean emailAccountBean;
    private String subject;
    private String recipient;
    private String content;
    private List<File> attachments = new ArrayList<>();

    public EmailSenderService(EmailAccountBean emailAccountBean,
                              String subject, String recipient,
                              String content,
                              List<File> attachments) {

        this.emailAccountBean = emailAccountBean;
        this.subject = subject;
        this.recipient = recipient;
        this.content = content;
        this.attachments = attachments;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {

                try {
//SetUp:
                    Session session = emailAccountBean.getSession();
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(emailAccountBean.getEmailAddress());
                    message.addRecipients(Message.RecipientType.TO, recipient);
                    message.setSubject(subject);

                    //Setting the content:
                    Multipart multipart = new MimeMultipart();
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setContent(content, "text/html");
                    multipart.addBodyPart(messageBodyPart);

                    //adding attachments:
                    if(attachments.size() >0){
                        for(File file: attachments){
                            MimeBodyPart messageBodyPartAttach = new MimeBodyPart();
                            DataSource source = new FileDataSource(file.getAbsolutePath());
                            messageBodyPartAttach.setDataHandler(new DataHandler(source));
                            messageBodyPartAttach.setFileName(file.getName());
                            multipart.addBodyPart(messageBodyPartAttach);
                        }
                    }
                    message.setContent(multipart);

                    //Sending the message:
                    Transport transport = session.getTransport();
                    transport.connect(emailAccountBean.getProperties().getProperty("outgoingHost"), // zeby dostac smtp.protocol z gmail
                            emailAccountBean.getEmailAddress());
                            emailAccountBean.getPassword();
                    transport.sendMessage(message, message.getAllRecipients());
                    transport.close();

                result = EmailConstants.MESSAGE_SENT_OK;


                } catch (Exception e) {
                    result = EmailConstants.MESSAGE_SENT_ERROR;
                    e.printStackTrace();
                }

                return null;
            }
        };
    }
}























