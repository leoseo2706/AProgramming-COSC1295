package core;

import core.controller.Controller;
import core.controller.UIController;
import core.model.Student;
import core.utils.Utils;
import core.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller cliController = new Controller();
        CompletableFuture<List<Student>> studentFuture = CompletableFuture.supplyAsync(() -> {
            return cliController.preloadStudent();
        });

        studentFuture.get(); // blocking to get first
        CompletableFuture.runAsync(() -> { // then start in another thread
            cliController.commandLoop();
        });

        // blocking here
        MainView view = new MainView();
        new UIController(view, studentFuture.get());
        Scene scene = new Scene(view.asParent(),700, 700);
        Utils.loadStyleResource(scene);
        primaryStage.setTitle("COSC1295 - Assignment");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args){
        Utils.loadResource();
        launch(args);
    }
}
