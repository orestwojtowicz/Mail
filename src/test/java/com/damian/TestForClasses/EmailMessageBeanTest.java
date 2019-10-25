package com.damian.TestForClasses;

import com.damian.model.formatValues.FormatSizeValues;
import com.damian.model.messageBeanContainer.EmailMessageBean;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.Assertions;

import javax.mail.Message;
import javax.mail.internet.MimeBodyPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;

public class EmailMessageBeanTest {

    private EmailMessageBean emailMessageBean;
    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleObjectProperty<FormatSizeValues> size;
    private Message messageReference;
    private SimpleObjectProperty<Date> date;
    private List<MimeBodyPart> attachmentsList = new ArrayList<>();
    private StringBuffer attachmentsNames = new StringBuffer();

    private MimeBodyPart mbp;


    @Test
    public void addAttachmentTest() throws Exception {

        Assert.assertTrue(attachmentsList.size() == 0);
        attachmentsList.add(mbp);
        Assert.assertTrue(attachmentsList.size() == 1);
        attachmentsList.add(mbp);
        Assert.assertTrue(attachmentsList.size() == 2);
        attachmentsList.add(mbp);
        Assert.assertTrue(attachmentsList.size() == 3);
    }

    @Test
    public void clearAttachmentTest() {
        attachmentsList.add(mbp);
        Assert.assertTrue(attachmentsList.size() == 1);
        emailMessageBean.clearAttachments();
        Assert.assertTrue(attachmentsList.size() == 0);


    }


}


