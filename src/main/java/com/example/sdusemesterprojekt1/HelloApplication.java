package com.example.sdusemesterprojekt1;

import EventColliders.Collider;
import EventColliders.SolidCollider;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import worldOfZuul.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private Game game = new Game();

    @Override
    public void start(Stage stage) throws IOException {
        game.showScene("coast");
    }

    public static void main(String[] args) {
        launch();
    }
}