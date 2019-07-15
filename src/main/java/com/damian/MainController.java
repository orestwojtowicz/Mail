package com.damian;




import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button button1;

    @FXML
    private WebView messageRenderer;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectColumn;

    @FXML
    private TableColumn<EmailMessageBean, String> senderColumn;

    @FXML
    private TableColumn<EmailMessageBean, String> sizeColumn;

    @FXML
    private TableView<EmailMessageBean> emailTableView;


    @FXML
    void ButtonClick(ActionEvent event) {
        System.out.println("Button clicked");
    }

    final ObservableList<EmailMessageBean> data = FXCollections.observableArrayList(
            new EmailMessageBean("Hello from", "ojcu@gmail.com", 555000),
            new EmailMessageBean("Hello from ojcu", "ojcu@gmail.com", 5550),
            new EmailMessageBean("Hello from anetka", "trociny@!", 5),
            new EmailMessageBean("Hello from dawid", "pocz@gmail.com", 2)

    );


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        messageRenderer.getEngine().loadContent("<html>Ojcu is the best</html>");

        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("sender"));
        senderColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        emailTableView.setItems(data);




        sizeColumn.setComparator(new Comparator<String>() {

            Integer int1, int2;
            @Override
            public int compare(String s, String t1) {
                int1 = EmailMessageBean.formattedValues.get(s);
                int2 = EmailMessageBean.formattedValues.get(t1);
                return int1.compareTo(int2);
            }
        });



        button1.setOnAction((event -> System.out.println("Clicked")));

    }


}

