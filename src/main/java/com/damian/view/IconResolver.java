package com.damian.view;


/*
 * method for setting new icons
 *
 * */


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;


public class IconResolver {


    public static IconResolver iconResolver = new IconResolver();

    public Node resolveIcon(String treeIconName) {

        String lowerCaseTreeItemValue = treeIconName.toLowerCase();
        ImageView returnIconName = new ImageView();

        try {
            if(lowerCaseTreeItemValue.contains("[gmail]")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/inbox-flat.png")));
            } else if(lowerCaseTreeItemValue.contains("@")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/mail-open-flat.png")));
            } else if(lowerCaseTreeItemValue.contains("wysłane")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/sent.png")));
            } else if(lowerCaseTreeItemValue.contains("spam")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/spam.png")));
            } else if(lowerCaseTreeItemValue.contains("folder")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/folder.png")));
            } else if(lowerCaseTreeItemValue.contains("kosz")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/trash.png")));
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error in resolving Icon, wrong path");
            returnIconName = new ImageView();
        }

        returnIconName.setFitHeight(20);
        returnIconName.setFitWidth(20);

        return returnIconName;

    }









}

 /*   //-------------------TreeView Section START-----------------------

    final Node emailImage = new ImageView(
            new Image(getClass().getResourceAsStream("/img/mail-open-flat.png")));
        ((ImageView) emailImage).setFitHeight(25);
                ((ImageView) emailImage).setFitWidth(25);


final  Node inboxImage = new ImageView(
        new Image(getClass().getResourceAsStream("/img/inbox-flat.png")));
        ((ImageView) inboxImage).setFitHeight(25);
        ((ImageView) inboxImage).setFitWidth(25);


final Node folderImage = new ImageView(
        new Image(getClass().getResourceAsStream("/img/folder.png")));
        ((ImageView) folderImage).setFitHeight(25);
        ((ImageView) folderImage).setFitWidth(25);

final  Node folderImage1 = new ImageView(
        new Image(getClass().getResourceAsStream("/img/folder.png")));
        ((ImageView) folderImage1).setFitHeight(25);
        ((ImageView) folderImage1).setFitWidth(25);



final  Node spamImage = new ImageView(
        new Image(getClass().getResourceAsStream("/img/spam.png")));
        ((ImageView) spamImage).setFitHeight(25);
        ((ImageView) spamImage).setFitWidth(25);


final  Node trashImage = new ImageView(
        new Image(getClass().getResourceAsStream("/img/trash.png")));
        ((ImageView) trashImage).setFitHeight(25);
        ((ImageView) trashImage).setFitWidth(25);


        TreeItem<String> root = new TreeItem<>(myAddressRoot,emailImage);
        root.setExpanded(true);

        TreeItem<String> inbox = new TreeItem<>("inbox", inboxImage);
        TreeItem<String> sent = new TreeItem<>("sent",sentImage);
        TreeItem<String> spam = new TreeItem<>("spam", spamImage);
        TreeItem<String> folder = new TreeItem<>("folder", trashImage);


        // root tree structure

        root.getChildren().addAll(inbox, sent,folder, spam);

        // structure for children of inbox, folder, sent, spam

        TreeItem<String> subSent1 = new TreeItem<>("subSent1", folderImage);
        TreeItem<String> subSent2 = new TreeItem<>("subSent1", folderImage1);


        sent.setExpanded(true);
        sent.getChildren().addAll(subSent1,subSent2);


        emailFolderTreeView.setRoot(root);

//----------------TreeView Section END------------------------*/