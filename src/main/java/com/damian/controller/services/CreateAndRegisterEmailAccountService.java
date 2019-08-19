package com.damian.controller.services;

import com.damian.imap.EmailAccountBean;
import com.damian.imap.EmailConstants;
import com.damian.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CreateAndRegisterEmailAccountService extends Service<Integer> {

    private String emailAddress;
    private String password;
    private EmailFolderBean<String> folderRoot;




    public CreateAndRegisterEmailAccountService(String emailAddress, String password,
                                                EmailFolderBean<String> folderRoot) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.folderRoot = folderRoot;

    }



    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>(){
            @Override
            protected Integer call() throws Exception {
                EmailAccountBean emailAccount = new EmailAccountBean(emailAddress, password);
                if(emailAccount.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEDED){

                    EmailFolderBean<String> emailFolderBean = new EmailFolderBean<String>(emailAddress);

                    folderRoot.getChildren().add(emailFolderBean);

                    FetchFoldersService fetchFoldersService = new FetchFoldersService(emailFolderBean, emailAccount);

                    fetchFoldersService.start();
                }
                return emailAccount.getLoginState();
            }

        };
    }

}
