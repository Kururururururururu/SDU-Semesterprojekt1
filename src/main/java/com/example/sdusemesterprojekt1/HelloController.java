package com.example.sdusemesterprojekt1;

import EventColliders.Collider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import worldOfZuul.Game;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private static Game game = new Game();


    private static ArrayList<Collider> colliders = new ArrayList<>();
    @FXML
    private GridPane background;
    @FXML
    private Pane player;

    public HelloController(Game tgame) {
        game = tgame;
    }

    @FXML
    public static void movePlayer(String direction, GridPane background, Pane player) {
        switch (direction) {
            //Move player, and keep it within constraints.
            case "up" -> {
                if(tileIsWalkable(background.getRowIndex(player), background.getColumnIndex(player), background, player, "up")) {
                    background.setRowIndex(player, background.getRowIndex(player) - 1);
                }
            }
            case "down" -> {
                if(tileIsWalkable(background.getRowIndex(player), background.getColumnIndex(player), background, player, "down")) {
                    background.setRowIndex(player, background.getRowIndex(player) + 1);
                }
            }
            case "left" -> {
                if(tileIsWalkable(background.getRowIndex(player), background.getColumnIndex(player), background, player, "left")) {
                    background.setColumnIndex(player, background.getColumnIndex(player) - 1);
                }
            }
            case "right" -> {
                if(tileIsWalkable(background.getRowIndex(player), background.getColumnIndex(player), background, player, "right")) {
                    background.setColumnIndex(player, background.getColumnIndex(player) + 1);
                }
            }
        }
    }

    private static boolean tileIsWalkable(int y, int x, GridPane background, Pane player, String direction) {
        //Check if the tile is within the grid.
        switch (direction)  {
            case "up":
                if (y - 1 < 0) {
                    return false;
                }
                break;
            case "down":
                if (y + 1 > 9) {
                    return false;
                }
                break;
            case "left":
                if (x - 1 < 0) {
                    return false;
                }
                break;
            case "right":
                if (x + 1 > 19) {
                    return false;
                }
                break;
        }

        //Check if the tile is a collider.
        for (Collider collider : colliders) {
            if (collider.isColliding(background.getRowIndex(player), background.getColumnIndex(player), direction)) {
                collider.onCollision(game);
                return false;
            }
        }
        return true;
    }

    public static void addCollider(Collider collider) {
        colliders.add(collider);
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