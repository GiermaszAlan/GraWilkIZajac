package com.company;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;




public class mainFrame extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        GridPane gridPane = new GridPane();
        Scene scene = new  Scene(gridPane , 320, 150);



        gridPane.setMaxSize(640, 300);
        gridPane.setMinSize(160, 75);


        stage.setTitle("Main menu");

        Button newGame = new Button("New Game");
        Button highScores = new Button("High Scores");
        Button exit = new Button("Exit");

        gridPane.add(newGame,0,1);
        gridPane.add(highScores,0,2);
        gridPane.add(exit,0,3);

        newGame.setPrefSize(320,50);
        highScores.setPrefSize(320,50);
        exit.setPrefSize(320,50);

        newGame.setMaxSize(320,50);
        highScores.setMaxSize(320,50);
        exit.setMaxSize(320,50);

        newGame.setMinSize(320,50);
        highScores.setMinSize(320,50);
        exit.setMinSize(320,50);



        newGame.setOnAction(actionEvent -> {

            gameFrame gf = new gameFrame();
            try {
                gf.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        highScores.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                scoresFrame sf = new scoresFrame();
                try {
                    sf.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
            }
        });

        KeyCombination kc = new KeyCodeCombination(KeyCode.Q , KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN);

        scene.setOnKeyReleased(keyEvent -> {
            if (kc.match(keyEvent)){
                    System.exit(0);
            }

        });



        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });




        scene.getStylesheets().add("mainFrame.css");





        stage.setScene(scene);
        stage.setMinWidth(334);
        stage.setMinHeight(188);
        stage.setMaxWidth(334);
        stage.setMaxHeight(188);
        stage.show();
    }
}
