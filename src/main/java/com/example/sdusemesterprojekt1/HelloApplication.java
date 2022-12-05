package com.example.sdusemesterprojekt1;

import Misc.Money;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import worldOfZuul.Game;

import java.io.IOException;

public class HelloApplication extends Application {

    private Game game = new Game();

    @Override
    public void start(Stage stage) throws IOException {
        Font.loadFont(getClass().getResourceAsStream("font/joystixmonospace.ttf"), 14);
        game.showScene("coast");

    }

    public static void main(String[] args) {
        launch();
    }
}