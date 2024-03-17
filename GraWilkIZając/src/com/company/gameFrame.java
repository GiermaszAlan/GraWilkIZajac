package com.company;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class gameFrame extends Application {

    public boolean on = true;
    public StringProperty name =new SimpleStringProperty();

    public void start(Stage stage) throws Exception {





        FileInputStream background = null; //tło
        FileInputStream wolfR = null; //wilk z prawej
        FileInputStream wolfL = null; //wilk z lewej
        FileInputStream basketRU = null; //koszyk prawa góra
        FileInputStream basketRD = null; //koszyk prawy dół
        FileInputStream basketLU = null; //koszyk lewa góra
        FileInputStream basketLD = null; //koszyk lewy dół
        FileInputStream egg = null; //jajko
        FileInputStream eggEndL = null; //jajko rozbite lewe
        FileInputStream eggEndR = null; //jajko rozbite prawe
        try {
            background = new FileInputStream("animation/BackGround.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            wolfR = new FileInputStream("animation/wolfR.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            wolfL = new FileInputStream("animation/wolfL.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            basketRU = new FileInputStream("animation/basketRU.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            basketRD = new FileInputStream("animation/basketRD.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            basketLU = new FileInputStream("animation/basketLU.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            basketLD = new FileInputStream("animation/basketLD.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            egg = new FileInputStream("animation/egg.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            eggEndL = new FileInputStream("animation/eggEndL.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            eggEndR = new FileInputStream("animation/eggEndR.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Image gameBackground = new Image(background);
        ImageView backgroundView = new ImageView(gameBackground);
        backgroundView.setX(-325);
        backgroundView.setY(-185);



        Image basketLDI = new Image(basketLD);
        ImageView basketLDView = new ImageView(basketLDI);
        basketLDView.setX(-325);
        basketLDView.setY(-185);

        Image wolfRI = new Image(wolfR);
        ImageView wolfRView = new ImageView(wolfRI);
        wolfRView.setX(-325);
        wolfRView.setY(-185);

        Image wolfLI = new Image(wolfL);
        ImageView wolfLView = new ImageView(wolfLI);
        wolfLView.setX(-325);
        wolfLView.setY(-185);

        Image basketLUI = new Image(basketLU);
        ImageView basketLUView = new ImageView(basketLUI);
        basketLUView.setX(-325);
        basketLUView.setY(-185);

        Image basketRDI = new Image(basketRD);
        ImageView basketRDView = new ImageView(basketRDI);
        basketRDView.setX(-325);
        basketRDView.setY(-185);

        Image basketRUI = new Image(basketRU);
        ImageView basketRUView = new ImageView(basketRUI);
        basketRUView.setX(-325);
        basketRUView.setY(-185);


        Image eggI = new Image(egg);
        ImageView eggView = new ImageView(eggI);

        Image eggELI = new Image(eggEndL);
        ImageView eggEndLView = new ImageView(eggELI);


        Image eggERI = new Image(eggEndR);
        ImageView eggEndRView = new ImageView(eggERI);










        Stage gameFrame = new Stage();


        Group root = new Group(backgroundView);

        wolfLView.setVisible(true);
        wolfRView.setVisible(false);
        basketLDView.setVisible(false);
        basketLUView.setVisible(true);
        basketRDView.setVisible(false);
        basketRUView.setVisible(false);

        root.getChildren().add(wolfLView);
        root.getChildren().add(wolfRView);
        root.getChildren().add(basketLUView);
        root.getChildren().add(basketLDView);
        root.getChildren().add(basketRUView);
        root.getChildren().add(basketRDView);
        root.getChildren().add(eggView);
        root.getChildren().add(eggEndRView);
        root.getChildren().add(eggEndLView);

        Label czas = new Label("time:"+0);
        root.getChildren().add(czas);
        czas.setLayoutX(230);
        czas.setLayoutY(20);
        Label pkt = new Label("score: "+0);
        root.getChildren().add(pkt);
        pkt.setLayoutX(380);
        pkt.setLayoutY(20);




        Thread t = new Thread(){

            int broke;
            boolean fine;
            int points;
            long Tstart;
            long Tstop;
            @Override
            public void run()

            {


                Tstart = System.currentTimeMillis();
                Path path = new Path();
                Path pathL = new Path();
                Path pathR = new Path();

                boolean uruchomZjazdKolejnegoJajka=true;
                boolean uruchomRozbicieJajka=false;

                int a=0;
                while (on) {

                    PathTransition pathT = new PathTransition();
                    PathTransition pathTEndL = new PathTransition();
                    PathTransition pathTEndR = new PathTransition();
                    if (uruchomZjazdKolejnegoJajka) {
                        a = (int)(Math.random()*4);
                        pathT.setNode(eggView);
                        pathT.setDuration(Duration.millis(1500));
                        path = new Path();
                        switch (a) {

                            case 0:
                                path.getElements().add(new MoveTo(415, 135));
                                path.getElements().add(new LineTo(320, 190));
                                break;
                            case 1:
                                path.getElements().add(new MoveTo(415, 220));
                                path.getElements().add(new LineTo(320, 280));
                                break;
                            case 2:
                                path.getElements().add(new MoveTo(-105, 140));
                                path.getElements().add(new LineTo(-30, 185));
                                break;
                            case 3:
                                path.getElements().add(new MoveTo(-105, 220));
                                path.getElements().add(new LineTo(-30, 270));
                                break;
                        }
                        pathT.setPath(path);
                        pathT.setCycleCount(1);
                    } else if (uruchomRozbicieJajka) {
                        path=new Path();
                        pathT.setDuration(Duration.millis(500));
                        if (a == 0 || a == 1) {
                            pathT.setNode(eggEndRView);
                            path.getElements().add(new MoveTo(220, 150));
                            path.getElements().add(new LineTo(225, 150));
                        }
                        if (a == 2 || a == 3) {
                            pathT.setNode(eggEndLView);
                            path.getElements().add(new MoveTo(330, 150));
                                path.getElements().add(new LineTo(335, 150));
                        }
                        pathT.setPath(path);

                    } else {
                        System.out.println("nie wiadomo którą animację ustawiać");
                    }

                    System.out.println("broke" + broke);
                    pathT.play();




                    System.out.println("Points: " + points);

                    while (on) {
                        Animation.Status s = pathT.getStatus();
                        if (s == Animation.Status.STOPPED) {
                            break;
                        } else {

                            try {
                                this.sleep(100);
                                long now = System.currentTimeMillis();
                                Platform.runLater(() -> czas.setText("time: "+ (((now-Tstart)/1000)/60)+ ":" + (((now-Tstart)/1000)%60) ));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                    if (uruchomZjazdKolejnegoJajka) {
                        switch (a) {
                            case 0:
                                if (wolfRView.isVisible() && basketRUView.isVisible()) {
                                    points++;
                                    fine=true;
                                } else {
                                    fine = false;
                                }
                                ;
                                break;
                            case 1:
                                if (wolfRView.isVisible() && basketRDView.isVisible()) {
                                    points++;
                                    fine=true;
                                } else {
                                    fine = false;
                                }
                                ;
                                break;
                            case 2:
                                if (wolfLView.isVisible() && basketLUView.isVisible()) {
                                    points++;
                                    fine=true;
                                } else {
                                    fine = false;
                                }
                                ;
                                break;
                            case 3:
                                if (wolfLView.isVisible() && basketLDView.isVisible()) {
                                    points++;
                                    fine=true;
                                } else {
                                    fine = false;
                                }

                                break;
                        }
                        Platform.runLater(() -> pkt.setText("score: "+points));


                        if (!fine) {
                            broke++;
                            uruchomRozbicieJajka = true;
                            uruchomZjazdKolejnegoJajka = false;
                        }
                    } else if (uruchomRozbicieJajka) {
                        if (broke == 4) {
                            System.out.println("End");
                            ColorAdjust blackout = new ColorAdjust();
                            blackout.setBrightness(-0.7);
                            backgroundView.setEffect(blackout);
                            backgroundView.setCache(true);
                            backgroundView.setCacheHint(CacheHint.SPEED);
                            wolfLView.setEffect(blackout);
                            wolfRView.setEffect(blackout);
                            basketRUView.setEffect(blackout);
                            basketLUView.setEffect(blackout);
                            basketRDView.setEffect(blackout);
                            basketLDView.setEffect(blackout);
                            eggEndLView.setEffect(blackout);
                            eggEndRView.setEffect(blackout);
                            eggView.setEffect(blackout);

                            Tstop = System.currentTimeMillis();
                            final CountDownLatch doneLatch = new CountDownLatch(1);
                            Platform.runLater(() -> {new inputName(stage, name,doneLatch);});
                            try {
                                doneLatch.await();
                            } catch (InterruptedException e) {}


                            String timeS = ((Tstop-Tstart)/1000)/60+"min "+((Tstop-Tstart)/1000)%60+"s";


score s = new score(name.getValueSafe(), timeS, points);


                            ArrayList<score> sArray = null;
                            try {
                                FileInputStream fis
                                        = null;
                                try {
                                    fis = new FileInputStream("scores.txt");
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                ObjectInputStream ois = null;
                                try {
                                    ois = new ObjectInputStream(fis);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    sArray = (ArrayList<score>) ois.readObject();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }catch(Exception e){sArray = new ArrayList<>();}



                            sArray.add(s);
                            FileOutputStream fos
                                    = null;
                            try {
                                fos = new FileOutputStream("scores.txt");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            ObjectOutputStream oos
                                    = null;
                            try {
                                oos = new ObjectOutputStream(fos);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                System.out.println(s.toString());
                                oos.writeObject(sArray);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            on = false;
                            this.stop();

                        }
                        uruchomRozbicieJajka = false;
                        uruchomZjazdKolejnegoJajka = true;
                    }



                }





            }



        };

t.start();


        




        KeyCombination kc = new KeyCodeCombination(KeyCode.Q , KeyCodeCombination.CONTROL_DOWN, KeyCodeCombination.SHIFT_DOWN);

        Scene scene = new  Scene(root , 630, 383);

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println(event.getCode());
                switch (event.getCode()){

                    case UP:
                        if(wolfRView.isVisible() && basketRDView.isVisible()) {
                            basketRUView.setVisible(true);
                            basketRDView.setVisible(false);
                        }
                        if(wolfLView.isVisible() && basketLDView.isVisible()){
                            basketLUView.setVisible(true);
                            basketLDView.setVisible(false);
                        }

                        break;
                    case DOWN:
                        if(wolfRView.isVisible() && basketRUView.isVisible()) {
                            basketRUView.setVisible(false);
                            basketRDView.setVisible(true);
                        }
                        if(wolfLView.isVisible() && basketLUView.isVisible()){
                            basketLUView.setVisible(false);
                            basketLDView.setVisible(true);
                        }

                        break;
                    case LEFT:
                        if(wolfRView.isVisible() && basketRUView.isVisible()){
                            wolfLView.setVisible(true);
                            basketLUView.setVisible(true);
                            wolfRView.setVisible(false);
                            basketRUView.setVisible(false);
                        }
                        if(wolfRView.isVisible() && basketRDView.isVisible()){
                            wolfLView.setVisible(true);
                            basketLDView.setVisible(true);
                            wolfRView.setVisible(false);
                            basketRDView.setVisible(false);
                        }
                        break;
                    case RIGHT:
                        if(wolfLView.isVisible() && basketLUView.isVisible()){
                            wolfRView.setVisible(true);
                            basketRUView.setVisible(true);
                            wolfLView.setVisible(false);
                            basketLUView.setVisible(false);
                        }
                        if(wolfLView.isVisible() && basketLDView.isVisible()){
                            wolfRView.setVisible(true);
                            basketRDView.setVisible(true);
                            wolfLView.setVisible(false);
                            basketLDView.setVisible(false);
                        }
                        break;

                }


               if (kc.match(event)){
                    mainFrame mf = new mainFrame();
                    t.stop();
                    stage.close();
                    try {
                        mf.start(new Stage());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Button menu = new Button("Main menu");
        root.getChildren().add(menu);
        menu.setLayoutX(540);
        menu.setLayoutY(350);

        menu.setOnAction(actionEvent -> {

            mainFrame mf = new mainFrame();
            t.stop();
            try {
                mf.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            stage.close();
        });


        Button exit = new Button("Exit");
        root.getChildren().add(exit);
        exit.setLayoutX(20);
        exit.setLayoutY(350);

        exit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //    stage.close();
                System.exit(0);
            }

        });








        scene.getStylesheets().add("gameFrame.css");

            gameFrame.setScene(scene);
               gameFrame.setMinWidth(644);
               gameFrame.setMinHeight(416);
               gameFrame.setMaxWidth(644);
               gameFrame.setMaxHeight(416);
            gameFrame.show();



    }
}
