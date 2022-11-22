package com.example.sdusemesterprojekt1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import worldOfZuul.Game;

import java.io.IOException;

public class HelloApplication extends Application {

    private Stage gameStage;
    private HelloController controller = new HelloController();
    private Game game;
    @Override
    public void start(Stage stage) throws IOException {
        showScene("coast");
    }

    public void showScene(String sceneName)  {
        try {
            if(gameStage == null) {
                gameStage = new Stage();
                gameStage.setTitle("Green Watts");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName + ".fxml"));
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            gameStage.setScene(scene);
            scene.setOnKeyPressed(event -> keypressHandler(event));
            gameStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void keypressHandler(KeyEvent event) {
        //Handle all keypresses here.
        switch (event.getCode())  {
            case W:
                HelloController.movePlayer("up", controller.getBackground(), controller.getPlayer());
                break;

            case S:
                HelloController.movePlayer("down", controller.getBackground(), controller.getPlayer());
                break;

            case A:
                HelloController.movePlayer("left", controller.getBackground(), controller.getPlayer());
                break;

            case D:
                HelloController.movePlayer("right", controller.getBackground(), controller.getPlayer());
                break;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}