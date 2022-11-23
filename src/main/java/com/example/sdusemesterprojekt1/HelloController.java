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
            //Move player, and keep it within constraints.
            case "up" -> { if(background.getRowIndex(player) > 0) { background.setRowIndex(player, background.getRowIndex(player) - 1); } }
            case "down" -> { if(background.getRowIndex(player) < 9) { background.setRowIndex(player, background.getRowIndex(player) + 1); } }
            case "left" -> { if(background.getColumnIndex(player) > 0) { background.setColumnIndex(player, background.getColumnIndex(player) - 1); } }
            case "right" -> { if(background.getColumnIndex(player) < 19) { background.setColumnIndex(player, background.getColumnIndex(player) + 1); } }
        }
    }

    //onClick calls from FXML
    @FXML
    public void onBagButtonClick() {
        System.out.println("Bag");
    }
    @FXML
    public void onMapButtonClick() {
        System.out.println("Map");
    }
    @FXML
    public void onMagButtonClick() {
        System.out.println("Magnifying Glass");
    }
    @FXML
    public void onHandButtonClick() {
        System.out.println("Hand");
    }
    @FXML
    public void onTalkButtonClick() {
        System.out.println("Talk");
    }
    @FXML
    public void onMenuButtonClick() {
        System.out.println("Menu");
    }
    @FXML
    public void onHelpButtonClick() {
        System.out.println("Help");
    }
    //End onClick calls from FXML

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