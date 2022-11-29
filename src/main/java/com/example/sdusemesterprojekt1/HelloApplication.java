package com.example.sdusemesterprojekt1;

import Misc.Money;
import javafx.application.Application;
import javafx.stage.Stage;
import worldOfZuul.Game;

import java.io.IOException;

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