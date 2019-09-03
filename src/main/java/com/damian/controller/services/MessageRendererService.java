package com.damian.controller.services;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;


import com.damian.model.messageBeanContainer.EmailMessageBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.web.WebEngine;

public class MessageRendererService extends Service<Void> {
	
	
	private EmailMessageBean messageToRender;
	private WebEngine messageRendererEngine;
	private StringBuffer sb = new StringBuffer();
	
	
	

	public MessageRendererService(WebEngine messageRendererEngine) {
		this.messageRendererEngine = messageRendererEngine;
	}

	// dziala to na glownym watku wiec nie bedzie wyjatku
	public void setMessageToRender(EmailMessageBean messageToRender){
		this.messageToRender = messageToRender;

		this.setOnSucceeded(event -> loadMessage());
	}


	@Override
	protected Task<Void> createTask() {
		return new Task<Void>(){
			@Override
			protected Void call() {
				renderMessage();
				return null;
			}			
		};
	}
	
	
	// https://javaee.github.io/javamail/FAQ#mainbody
	
	
	private void renderMessage(){
		// clear the sb:
		sb.setLength(0);
		// clear attachments
		messageToRender.clearAttachments();

		Message message = messageToRender.getMessageReference();
		try {
			String messageType = message.getContentType();
			if(messageType.contains("TEXT/HTML") ||
					messageType.contains("TEXT/PLAIN") ||
					messageType.contains("text")){
				sb.append(message.getContent().toString());

			} else if(messageType.contains("multipart")){
				Multipart mp = (Multipart)message.getContent();
				int multiPartMessageCounter = mp.getCount()-1;

				for (int i = multiPartMessageCounter; i >= 0; i--) {
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

						//here the attachments are handled
					} else if(contentType.toLowerCase().contains("application") ||
							 contentType.toLowerCase().contains("image") ||
							 contentType.toLowerCase().contains("audio")){
						MimeBodyPart mbp = (MimeBodyPart)bp;

						messageToRender.addAttachment(mbp);

						// Sometimes the text content of the message is encapsulated in another multipart,
						// so we have to iterate again through it.
					}else if(bp.getContentType().contains("multipart")){
						Multipart mp2 = (Multipart)bp.getContent();
						for (int j = mp2.getCount()-1; j >= 0; j--) {
							BodyPart bp2 = mp2.getBodyPart(i);
							if((bp2.getContentType().contains("TEXT/HTML") ||
									bp2.getContentType().contains("TEXT/PLAIN") ) ){
								sb.append(bp2.getContent().toString());
							}
							// end for(int j = mp2.getCount()-1; j >= 0; j--)
						}
					}
					// end for(int i = multiPartMessageCounter; i >= 0; i--)
				}

			}

		} catch (Exception e) {
			System.out.println("Exception during vizualization of message: ");
			e.printStackTrace();
		}
		
		
	}

/*	@Override
	public void run() {
		renderMessage();
		
	}*/


// only call once the message is loaded, handle info about attachments
	private void loadMessage() {
		messageRendererEngine.loadContent(sb.toString());
	}


}




/*

	// suppose 'message' is an object of type Message
	String contentType = message.getContentType();

if (contentType.contains("multipart")) {
		// this message may contain attachment
		}
		Then we must iterate through each part in the multipart to identify which part contains the attachment, as follows:

		Multipart multiPart = (Multipart) message.getContent();

		for (int i = 0; i < multiPart.getCount(); i++) {
		MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(i);
		if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
		// this part is attachment
		// code to save attachment...
		}
		}

*/













