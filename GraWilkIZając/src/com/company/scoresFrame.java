package com.company;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class scoresFrame extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("High Scores");
        VBox layout = new VBox();
        Scene scene = new Scene(layout,300, 250);

        ArrayList<score> sArray = new ArrayList<>();
        FileInputStream fis 
                = new FileInputStream("scores.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
         sArray = (ArrayList<score>) ois.readObject();
        ListView listView = new ListView<>();
         for(score s : sArray) {
             System.out.println(s.toString());
             listView.getItems().add(s);
         }




        layout.getChildren().add(listView);
        stage.setScene(scene);
        stage.show();


    }
}
