package com.damian.model.folder;

import com.damian.view.IconResolver;
import javafx.scene.control.TreeItem;

public class EmailFolderBean<T> extends TreeItem<String> {

    private String name;
    private String completeName;





    public EmailFolderBean(String value) {
        super(value, IconResolver.iconResolver.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
        this.setExpanded(true);
    }



    public EmailFolderBean(String value, String completeName) {
        super(value, IconResolver.iconResolver.resolveIcon(value));
        this.name = value;
        this.completeName = completeName;
       }




}
