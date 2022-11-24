package com.example.sdusemesterprojekt1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import worldOfZuul.Game;

import java.io.IOException;

public class HelloApplication extends Application {

    private Game game;
    private static Stage gameStage;
    private HelloController controller = new HelloController(game);
    @Override
    public void start(Stage stage) throws IOException {
        showScene("hub");
    }

    private void showScene(String sceneName)  {
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
            //HelloController.checkColliders(); // TODO fix when function is added.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SshowScene(String sceneName)  {
        try {
            if(gameStage == null) {
                gameStage = new Stage();
                gameStage.setTitle("Green Watts");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            gameStage.setScene(scene);
            gameStage.show();
            //HelloController.checkColliders(); // TODO fix when function is added.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void keypressHandler(KeyEvent event) {
        //Handle all keypresses here.
        switch (event.getCode())  {
            case W -> { HelloController.movePlayer("up", controller.getBackground(), controller.getPlayer()); }
            case S -> { HelloController.movePlayer("down", controller.getBackground(), controller.getPlayer()); }
            case A -> { HelloController.movePlayer("left", controller.getBackground(), controller.getPlayer()); }
            case D -> { HelloController.movePlayer("right", controller.getBackground(), controller.getPlayer()); }
            case I -> { controller.onBagButtonClick(); }
            case M -> { controller.onMapButtonClick(); }
            case G -> { controller.onMagButtonClick(); }
            case H -> { controller.onHandButtonClick(); }
            case T -> { controller.onTalkButtonClick(); }
            case ESCAPE -> { controller.onMenuButtonClick(); }
            case F1 -> { controller.onHelpButtonClick(); }
        }
    }




    public static void main(String[] args) {
        launch();
    }
}