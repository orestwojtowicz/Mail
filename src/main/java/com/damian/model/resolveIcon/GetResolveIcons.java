package com.damian.model.resolveIcon;

import com.damian.model.folder.EmailFolderBean;

public class GetResolveIcons {

    private EmailFolderBean<String> selectedFolder;


    public EmailFolderBean<String> getSelectedFolder() {
        return selectedFolder;
    }

    public void setSelectedFolder(EmailFolderBean<String> selectedFolder) {
        this.selectedFolder = selectedFolder;
    }


}
