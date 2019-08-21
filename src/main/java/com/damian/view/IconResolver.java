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
            if(lowerCaseTreeItemValue.contains("inbox")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/message.png")));
            } else if(lowerCaseTreeItemValue.contains("@")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/adm2.png")));
            } else if(lowerCaseTreeItemValue.contains("drafts") || lowerCaseTreeItemValue.contains("obrazy")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/drafts.png")));
            } else if(lowerCaseTreeItemValue.contains("important") || lowerCaseTreeItemValue.contains("ważne")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/important.png")));
            } else if(lowerCaseTreeItemValue.contains("sent") || lowerCaseTreeItemValue.contains("wysłane")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/sent.png")));
            } else if(lowerCaseTreeItemValue.contains("spam")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/spam.png")));
            } else if(lowerCaseTreeItemValue.contains("starred") || lowerCaseTreeItemValue.contains("oznaczone gwiazdką")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/star.png")));
            } else if(lowerCaseTreeItemValue.contains("trash") || lowerCaseTreeItemValue.contains("kosz")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/trash.png")));
            } else if(lowerCaseTreeItemValue.contains("gmail")) {
                returnIconName = new ImageView(new Image(getClass().getResourceAsStream("/img/gmail.png")));
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
            System.out.println("Error in resolving Icon, wrong path");

        }

        returnIconName.setFitHeight(24);
        returnIconName.setFitWidth(24);

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