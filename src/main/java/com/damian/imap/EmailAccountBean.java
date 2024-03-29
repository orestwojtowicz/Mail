package com.damian.imap;



import javax.mail.*;
import java.util.Properties;

public class EmailAccountBean {

    private String emailAddress;
    private String password;
    private Properties properties;
    private Session session;
    private Store store;


    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    private int loginState = EmailConstants.LOGIN_STATE_NOT_READY;
    public static final int MESSAGE_SENT_OK = 4;
    public static final int MESSAGE_SENT_ERROR = 5;

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

    public String getPassword() {
        return password;
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



}





































