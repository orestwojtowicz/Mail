package com.damian;

import com.damian.controller.MainController;
import com.damian.controller.services.FetchFoldersService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import org.mockito.Mock;

import java.util.concurrent.CompletableFuture;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;


public class MainTest extends ApplicationTest{




    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Main.class.getResource("fxml/main.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
        stage.toFront();
    }


    @Before
    public void setUpClaass() throws Exception {

        ApplicationTest.launch(Main.class);



      }

// http://blog.buildpath.de/how-to-test-javafx-services/ https://stackoverflow.com/questions/46778090/javafx-with-junit

   @Test
    public void A() {

       CompletableFuture<FetchFoldersService> service = new CompletableFuture<>();

           clickOn("#newMessage");
        // sleep(8000);
        verifyThat("#newMessage", hasText("New Message"));
          // verifyThat("#recipentField", hasText("krztuszenie@gmail.com"));

    }



 /*   interact(() -> {
        verifyThat("#recipentField", hasText("krztuszenie@gmail.com"));
    });
*/

// https://stackoverflow.com/questions/28978163/how-to-test-method-of-javafx-controller




   @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }




/*
FOR TAKS THAT TIME MUST BE LESS THAN ONE MINUTE

@Test
 public void testEnglishInput () {
     assertTimeout(ofMinutes(1), () -> {
         // Simulate task that takes more than 10 ms.
         System.out.println("CZEKAM SE ");

     });

 }*/

}



// addAccount
// newMessage














