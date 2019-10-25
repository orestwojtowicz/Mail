package com.damian.view;

/**
 * method for setting new icons
 * note: folders names from email
 * might be in different languages
 * here are only two cases, english + polish
 * icon source: http://www.iconarchive.com
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

