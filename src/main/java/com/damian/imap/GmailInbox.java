package com.damian.imap;

import javax.mail.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class GmailInbox {

    private static final String propertiesPath = "/media/damiass/Extreme SSD/Majlek0.1/src/main/resources/smtp.properties";

    public static void main(String[] args) {

        GmailInbox gmailInbox = new GmailInbox();
        gmailInbox.read();

    }


    public void read() {

        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File(propertiesPath)));
            Session session = Session.getDefaultInstance(props, null);

            Store store = session.getStore("imaps");
            store.connect("smtp.gmail.com", "****", "***");

            Folder inbox = store.getFolder("inbox");
            inbox.open(Folder.READ_ONLY);
            int messageCount = inbox.getMessageCount();

            System.out.println("Total Messages:- " + messageCount);

            Message[] messages = inbox.getMessages();
            System.out.println("------------------------------");

            for (int i = 0; i < 10; i++) {
                System.out.println("Mail Subject:- " + messages[i].getSubject());
            }

            inbox.close(true);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}








































