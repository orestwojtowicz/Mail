package com.damian.model;

public class Singleton {

    private Singleton() {}
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }

    private EmailMessageBean messageBean;

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }

    public EmailMessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(EmailMessageBean messageBean) {
        this.messageBean = messageBean;
    }
}
