package com.damian.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CreateAndRegisterEmailAccount extends Service<Integer> {

    private String emailAddress;
    private String password;



    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return null;
            }
        };
    }
}
