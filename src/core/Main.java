package core;

import core.controller.Controller;
import core.utils.Utils;
import core.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        // start in another thread
        CompletableFuture.runAsync(() -> {
            new Controller().commandLoop();
        });

        // blocking here
        MainView view = new MainView();
        Scene scene = new Scene(view.asParent(),800, 800);
        Utils.loadStyleResource(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        Utils.loadResource();
        launch(args);
    }
}
