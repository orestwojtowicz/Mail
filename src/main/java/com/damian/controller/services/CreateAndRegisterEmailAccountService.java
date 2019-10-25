package com.damian.controller.services;


import com.damian.model.ModelForMessages;
import com.damian.imap.EmailAccountBean;
import com.damian.imap.EmailConstants;
import com.damian.model.folder.EmailFolderBean;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class CreateAndRegisterEmailAccountService extends Service<Integer> {

    private String emailAddress;
    private String password;
    private EmailFolderBean<String> folderRoot;

    public CreateAndRegisterEmailAccountService(
            String emailAddress, String password,
            EmailFolderBean<String> folderRoot,
            ModelForMessages modelAccess) {

        this.emailAddress = emailAddress;
        this.password = password;
        this.folderRoot = folderRoot;
        //this.modelAccess = modelAccess;

    }



    @Override
    protected Task<Integer> createTask() {
    return new Task<Integer>(){
        @Override
        protected Integer call() {
            EmailAccountBean emailAccount = new EmailAccountBean(emailAddress, password);
            if(emailAccount.getLoginState() == EmailConstants.LOGIN_STATE_SUCCEDED){
                /**
                 *
                 * Bug with getting emailAddress, it will not work without setEmailAddress, NullPointer
                 *
                 * */

                emailAccount.setEmailAddress(emailAddress);

                ModelForMessages.modelForMessages.addAccount(emailAccount);


                EmailFolderBean<String> emailFolderBean = new EmailFolderBean<>(emailAddress);
                folderRoot.getChildren().add(emailFolderBean);
                FetchFoldersService fetchFoldersService = new FetchFoldersService(emailFolderBean, emailAccount, ModelForMessages.modelForMessages);
                fetchFoldersService.start();
            }
            return emailAccount.getLoginState();
        }

    };
}

}


























