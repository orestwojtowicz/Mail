package com.damian.imap;

import com.damian.model.EmailMessageBean;
import javafx.collections.ObservableList;

import javax.mail.*;
import java.util.Properties;

public class EmailAccountBean {


    private String emailAddress;
    private String password;
    private Properties properties;
    private Session session;
    private Store store;


    private int loginState = EmailConstants.LOGIN_STATE_NOT_READY;


    public String getEmailAddress() {
        return emailAddress;
    }


    public Properties getProperties() {
        return properties;
    }

    public Store getStore() {
        return store;
    }

    public Session getSession() {
        return session;
    }

    public int getLoginState() {
        return loginState;
    }

    public EmailAccountBean(String EmailAdress, String Password) {
        this.emailAddress = getEmailAddress();
        this.password = Password;


        properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("incomingHost", "imap.gmail.com");
        properties.put("outgoingHost", "smtp.gmail.com");



        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailAdress, password);
            }
        };

        // Connecting:

        session = Session.getInstance(properties, auth);


        try {
            // getting inf from session
            this.store = session.getStore();
            // connecting now
            store.connect(properties.getProperty("incomingHost"), emailAddress, password);
            loginState = EmailConstants.LOGIN_STATE_SUCCEDED;
            System.out.print("Connected successfully");
        } catch (Exception e) {
            System.out.println("Error while connecting");
            loginState = EmailConstants.LOGIN_STATE_FAILED_BY_CREDENTIALS;
        }


    }


/*    public void addEmailsToData(ObservableList<EmailMessageBean> data) {
        try {

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            for (int i = folder.getMessageCount(); i > 0; i--) {
                Message message = folder.getMessage(i);
                EmailMessageBean messageBean = new EmailMessageBean(message.getSubject(), message.getFrom()[0].toString(),
                        message.getSize(), "", message.getFlags().contains(Flags.Flag.SEEN));
                System.out.println("Got: " + messageBean);
                data.add(messageBean);
            }


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }*/
}





































