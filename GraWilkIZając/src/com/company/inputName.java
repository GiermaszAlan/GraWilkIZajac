package com.company;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.concurrent.CountDownLatch;

public class inputName {

        StackPane stack;
        Scene scene;
        TextField text;
        Button exit1;
        Stage window;

        public inputName(Stage primaryStage, StringProperty name, CountDownLatch cdl) {

            stack = new StackPane();

            scene = new Scene(stack, 330, 100);
            text = new TextField("Name");
            text.textProperty().bindBidirectional(name);
            text.setMinWidth(120);


            exit1 = new Button();
            exit1.setText("exit");
            exit1.setPrefWidth(200);

            exit1.setOnAction(new EventHandler<ActionEvent>() {

                                  @Override

                                  public void handle(ActionEvent event) {

                                      window.close();
                                      mainFrame mf = new mainFrame();
                                      try {
                                          mf.start(new Stage());
                                      } catch (Exception e) {
                                          e.printStackTrace();
                                      }
                                  }

                              }

            );

            VBox sBox = new VBox();
            sBox.setSpacing(5);
            sBox.getChildren().addAll(text, exit1);

            stack.getChildren().addAll(sBox);
            window = new Stage();
            window.setTitle("Name");
            window.setScene(scene);
            window.initModality(Modality.WINDOW_MODAL);
            window.initOwner(primaryStage);
            window.show();


        }



    }