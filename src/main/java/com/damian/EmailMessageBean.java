package com.damian;


import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class EmailMessageBean {

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;

    public static Map<String, Integer> formattedValues = new HashMap<>();

    public EmailMessageBean(String sender, String subject, int size) {

        this.sender = new SimpleStringProperty(subject);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(formatSize(size));

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
