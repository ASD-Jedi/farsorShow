package sample;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    private int heightBegin = 400;
    private int widthBegin = 200;

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setHeight(heightBegin);
        primaryStage.setWidth(widthBegin);

        primaryStage.setResizable(false);
        Settings set = new Settings();


        System.out.println(primaryStage.getHeight());
        System.out.println(primaryStage.getWidth());
        primaryStage.setTitle("River - Settings");

        primaryStage.setScene(set.settingsScene(primaryStage));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

