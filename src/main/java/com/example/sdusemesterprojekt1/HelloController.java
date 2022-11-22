package com.example.sdusemesterprojekt1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private GridPane background;
    @FXML
    private Pane player;

    @FXML
    public static void movePlayer(String direction, GridPane background, Pane player) {
        switch (direction) {
            case "up":
                background.setRowIndex(player, background.getRowIndex(player) - 1);
                break;
            case "down":
                background.setRowIndex(player, background.getRowIndex(player) + 1);
                break;
            case "left":
                background.setColumnIndex(player, background.getColumnIndex(player) - 1);
                break;
            case "right":
                background.setColumnIndex(player, background.getColumnIndex(player) + 1);
                break;
        }
    }

    public GridPane getBackground() {
        return this.background;
    }

    public Pane getPlayer() {
        return this.player;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}