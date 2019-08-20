package com.damian.controller.services;

import com.damian.model.EmailMessageBean;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;


public class MessageRendererService extends Service<Void> implements Runnable {

    private EmailMessageBean messageToRender;
    private WebEngine messageRendererEngine;

    private StringBuffer sb = new StringBuffer();



// https://javaee.github.io/javamail/FAQ#mainbody


    public MessageRendererService(WebEngine messageRendererEngine) {
        this.messageRendererEngine = messageRendererEngine;
    }



    public void setMessageToRender(EmailMessageBean messageToRender) {
        this.messageToRender = messageToRender;

    }



    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                renderMessage();
                return null;

            }
        };
    }










    private void renderMessage() {
        //clear the sb: zeby usunac zawartosc poprzedniej wiadomosci
        sb.setLength(0);
        Message message = messageToRender.getMessageReference();
        try {
            String messageType = message.getContentType();
            if(messageType.contains("TEXT/HTML") ||
                    messageType.contains("TEXT/PLAIN") ||
                    messageType.contains("text")){
                sb.append(message.getContent().toString());

            } else if(messageType.contains("multipart")){
                Multipart mp = (Multipart)message.getContent();
                for (int i = mp.getCount()-1; i >= 0; i--) {
                    BodyPart bp = mp.getBodyPart(i);
                    String contentType = bp.getContentType();
                    if(contentType.contains("TEXT/HTML") ||
                            contentType.contains("TEXT/PLAIN") ||
                            contentType.contains("mixed")||
                            contentType.contains("text")){
                        //Here the risk of incomplete messages are shown, for messages that contain both
                        //html and text content, but these messages are very rare;
                        if (sb.length()== 0) {
                            sb.append(bp.getContent().toString());
                        }

                        //here the attachments are handled TODO by someone who cares
                     }else if(contentType.toLowerCase().contains("application")){
                        MimeBodyPart mbp = (MimeBodyPart)bp;

                        //Sometimes the text content of the message is encapsulated in another multipart,
                        //so we have to iterate again through it.
                    }else if(bp.getContentType().contains("multipart")){
                        Multipart mp2 = (Multipart)bp.getContent();
                        for (int j = mp2.getCount()-1; j >= 0; j--) {
                            BodyPart bp2 = mp2.getBodyPart(i);
                            if((bp2.getContentType().contains("TEXT/HTML") ||
                                    bp2.getContentType().contains("TEXT/PLAIN") ) ){
                                sb.append(bp2.getContent().toString());
                            }
                        }
                    }
                }

            }
            messageRendererEngine.load(sb.toString());

        } catch (Exception e) {
            System.out.println("Exception while vizualizing message: ");
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        renderMessage();
    }


}


// StringBuilder nie jest thread safe
// StringBuffer jest thread safe