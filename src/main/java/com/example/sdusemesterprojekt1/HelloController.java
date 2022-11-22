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

    //onClick calls from FXML
    @FXML
    protected void onBagButtonClick() {
        System.out.println("Bag");
    }
    @FXML
    protected void onMapButtonClick() {
        System.out.println("Map");
    }
    @FXML
    protected void onMagButtonClick() {
        System.out.println("Magnifying Glass");
    }
    @FXML
    protected void onHandButtonClick() {
        System.out.println("Hand");
    }
    @FXML
    protected void onTalkButtonClick() {
        System.out.println("Talk");
    }
    @FXML
    protected void onMenuButtonClick() {
        System.out.println("Menu");
    }
    @FXML
    protected void onHelpButtonClick() {
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