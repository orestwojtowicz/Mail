package com.damian.controller.services;


import javafx.concurrent.Service;
import javafx.concurrent.Task;



public class TestService extends Service<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>(){
            @Override
            protected String call() throws Exception {

                Thread.sleep(5000);


                return "Returning String";
                }


            };

        }    }

