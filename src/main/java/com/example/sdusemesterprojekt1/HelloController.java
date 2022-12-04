package com.example.sdusemesterprojekt1;

import Characters.NPC;
import EventColliders.Collider;
import EventColliders.RoomchangeCollider;
import EventColliders.SolidCollider;
import Misc.Item;
import Misc.Money;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import worldOfZuul.Game;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.TimeUnit;


public class HelloController implements Initializable {

    private static Game game = new Game();
    private boolean mapOpenStatus = false;
    private boolean helpOpenStatus = false;
    private static boolean disableControls = false;
    private static ArrayList<Collider> colliders = new ArrayList<>();
    @FXML
    private GridPane background;
    @FXML
    private Pane player;
    @FXML
    private Pane npc;
    @FXML
    private Pane mapUnfold;
    @FXML
    private Label helpUnfoldText;
    @FXML
    private Pane helpUnfold;
    @FXML
    private Label balance;
    @FXML
    private AnchorPane inventorySubScene, shopSubScene;
    @FXML
    private Pane slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, shopslot1, shopslot2, shopslot3, shopslot4, shopslot5, shopslot6, shopslot7, shopslot8;
    @FXML
    private Label slot1label, slot2label, slot3label, slot4label, slot5label, slot6label, slot7label, slot8label;
    @FXML
    private Tooltip slot1tooltip, slot2tooltip, slot3tooltip, slot4tooltip, slot5tooltip, slot6tooltip, slot7tooltip, slot8tooltip;
    @FXML
    private RadioButton item1, item2, item3, item4, item5, item6, item7, item8;
    @FXML
    private Label shopname, shopitemlabel, shoppricelabel, itemname, itemprice, pointsavailable;
    @FXML
    private Button shopBuyButton;


    public HelloController(Game tgame) {
        game = tgame;
    }

    @FXML
    public static void movePlayer(String direction, GridPane background, Pane player) {
        if(disableControls){return;}
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

    public void updateBalanceGUI() {
        balance.setText(Money.getMoney().toString());
    }
    private static boolean tileIsWalkable(int y, int x, GridPane background, Pane player, String direction) {
        //Check if the tile is a collider.
        for (Collider collider : colliders) {
            if (collider.isColliding(background.getRowIndex(player), background.getColumnIndex(player), direction)) {
                //System.out.println("player is at: " + background.getColumnIndex(player) + ", " + background.getRowIndex(player) + " and is colliding with " + collider.getPosition());
                collider.onCollision(game);
                return false;
            }
        }


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

        return true;
    }

    public void checkColliders()    {
        colliders = new ArrayList<>();
        //System.out.println("Checking colliders");
        for(Node child : background.getChildren())  {
            if(child.getId() != null)   {
                if(child.getId().equals("solidcollider"))   {
                    //System.out.println("Solid collider found");
                    colliders.add(new SolidCollider(background.getRowIndex(child), background.getColumnIndex(child)));
                }
                if(child.getId().equals("roomchangecollider"))   {
                    //System.out.println("Roomchange collider found");
                    colliders.add(new RoomchangeCollider(background.getRowIndex(child), background.getColumnIndex(child), child.getAccessibleText()));
                }
            }
        }
    }

    //onClick calls from FXML
    @FXML
    public void onBagButtonClick() throws IOException {
        if(!inventorySubScene.getParent().getParent().isVisible() && !mapOpenStatus && !helpOpenStatus){
            disableControls = true;
            ArrayList<Pane> slots = new ArrayList<>(List.of(slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8));
            ArrayList<Label> slotlabels = new ArrayList<>(List.of(slot1label, slot2label, slot3label, slot4label, slot5label, slot6label, slot7label, slot8label));
            ArrayList<Tooltip> slottooltips = new ArrayList<>(List.of(slot1tooltip, slot2tooltip, slot3tooltip, slot4tooltip, slot5tooltip, slot6tooltip, slot7tooltip, slot8tooltip));

            ArrayList<Item> inv = game.getMainCharacter().getPlayer_inventory().getInventoryUniques();
            ArrayList<Integer> item_count = game.getMainCharacter().getPlayer_inventory().getInventoryUniquesCount();

            int slot = 0;
            for (Item i: inv) {
                String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + "Inventory-" + i.getType().replaceAll("\\s+","") + "16x16.png";
                BackgroundImage icon = new BackgroundImage(new Image( iconPath,48,48,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                slots.get(slot).setBackground(new Background(icon));
                slottooltips.get(slot).setOpacity(1);
                slottooltips.get(slot).setText(i.getType());
                slotlabels.get(slot).setText("x"+item_count.get(slot).toString());

                // For debugging
                System.out.println("*******************\nSlot: " + (slot+1) + "\nItem name: " +i.getType() + "\nItem count: " + item_count.get(slot) + "\nIcon url: " + iconPath + "\n*******************\n");

                slot ++;
            }
            inventorySubScene.getParent().getParent().setVisible(true);
        } else {
            onBagCloseButtonClick();
        }


    }
    @FXML
    public void onBagCloseButtonClick() {
        disableControls = false;

        ArrayList<Pane> slots = new ArrayList<>(List.of(slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8));
        ArrayList<Label> slotlabels = new ArrayList<>(List.of(slot1label, slot2label, slot3label, slot4label, slot5label, slot6label, slot7label, slot8label));
        ArrayList<Tooltip> slottooltips = new ArrayList<>(List.of(slot1tooltip, slot2tooltip, slot3tooltip, slot4tooltip, slot5tooltip, slot6tooltip, slot7tooltip, slot8tooltip));

        for (int i = 0; i < 8; i++) {
            slots.get(i).setBackground(null);
            slottooltips.get(i).setOpacity(0);
            slottooltips.get(i).setText(null);
            slotlabels.get(i).setText(null);
        }
        inventorySubScene.getParent().getParent().setVisible(false);
    }
    public void getSlots() {

    }
    @FXML
    public void onMapButtonClick() {
        if (!mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible() && !helpOpenStatus) {
            System.out.println("Opening Map");
            disableControls = true;
            mapUnfold.setVisible(true);
            mapOpenStatus = true;
        }
        else if (mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible()) {
            System.out.println("Closing Map");
            disableControls = false;
            mapUnfold.setVisible(false);
            mapOpenStatus = false;
        }
    }
    @FXML
    public void onMagButtonClick() {
        System.out.println("Magnifying Glass");
    }
    @FXML
    public void onHandButtonClick() {
        System.out.println("Hand");
        openShop();
        System.out.println(game.getRoomId());
    }

    @FXML
    public void onTalkButtonClick() {
        System.out.println("Talk");
        npcTalk();
    }
    @FXML
    public void onMenuButtonClick() {
        System.out.println("Menu");
    }
    @FXML
    public void onHelpButtonClick() {
        if (!mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible() && !helpOpenStatus) {
            System.out.println("Opening Help");
            disableControls = true;
            helpUnfold.setVisible(true);
            helpUnfoldText.setVisible(true);
            helpOpenStatus = true;
        }
        else if (helpOpenStatus && !inventorySubScene.getParent().getParent().isVisible()) {
            System.out.println("Closing Help");
            disableControls = false;
            helpUnfold.setVisible(false);
            helpUnfoldText.setVisible(false);
            helpOpenStatus = false;
        }

    }
    //End onClick calls from FXML

    public GridPane getBackground() {
        return this.background;
    }

    public Pane getPlayer() {
        return this.player;
    }


    //public BorderPane getSubScene() {
    //    return this.subScene;
    //}

    public Pane getNPC() {return this.npc; }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private boolean npcIsTalkable(GridPane background, Pane player, Pane npc) {
        //If the player is in 1 tile adjacent to the NPC, in one of the 9 possible directions, return true.
        int relativeDistanceX = Math.abs((background.getColumnIndex(player)-background.getColumnIndex(npc)));
        int relativeDistanceY = Math.abs((background.getRowIndex(player)-background.getRowIndex(npc)));
        if ((relativeDistanceX <= 1 && relativeDistanceY <= 1)) {
            return true;
        }
        else {
            return false;
        }
    }
    @FXML
    public void npcTalk() {
        if (npcIsTalkable(getBackground(), getPlayer(), getNPC())) {
            System.out.println("HELLO I CAN TALK");
        }
    }
    @FXML
    public void openShop() {

        if((game.getRoomId() == 0 || game.getRoomId() == 1 )&& npcIsTalkable(getBackground(), getPlayer(), getNPC())){
            if(!shopSubScene.getParent().getParent().isVisible()) {
                disableControls = true;
                shopSubScene.getParent().getParent().setVisible(true);

                System.out.println("Shop opens");
                ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
                ArrayList<Pane> slots = new ArrayList<>(List.of(shopslot1, shopslot2, shopslot3, shopslot4, shopslot5, shopslot6, shopslot7, shopslot8));
                ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));

                pointsavailable.setText("(Balance: " + Money.getMoney().toString() + "$)");

                for (int i = 0; i < currentShopItems.size(); i++) {
                    String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + "Inventory-" + currentShopItems.get(i).getType().replaceAll("\\s+", "") + "16x16.png";
                    BackgroundImage icon = new BackgroundImage(new Image(iconPath, 48, 48, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    slots.get(i).setBackground(new Background(icon));

                    radioButtons.get(i).setVisible(true);

                    System.out.println(currentShopItems.get(i).getType());

                }
            } else {
                onShopCloseButtonClick();
            }
        }
    }
    public void onShopItemSelected(){
        ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
        ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));
        int selection = getShopSelectedItem(currentShopItems, radioButtons);

        shopitemlabel.setVisible(true);
        shoppricelabel.setVisible(true);
        itemprice.setVisible(true);
        shopBuyButton.setVisible(true);
        itemprice.setStyle("-fx-font-weight: normal");

        itemname.setText(currentShopItems.get(selection).getType());
        itemprice.setText(currentShopItems.get(selection).getPrice().toString() + "$");
        pointsavailable.setText("(Balance: " + Money.getMoney().toString() + "$)");

        updateShopGUI(currentShopItems, selection);
    }
    public void onShopBuyButtonClick() throws InterruptedException {
        ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
        ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));
        int selection = getShopSelectedItem(currentShopItems, radioButtons);
        if(currentShopItems.get(selection).getPrice() > Money.getMoney()){
            System.out.println("Not enough $");
            String reset = pointsavailable.getText();
            pointsavailable.setText("(You need " + Math.abs(Money.getMoney()-currentShopItems.get(selection).getPrice()) + "$ more for that!)");
        } else {
            Money.removeMoney(game.getPointShop().currentShop(game.getRoomId()).get(selection).getPrice());
            game.getMainCharacter().addToInventory(game.getPointShop().currentShop(game.getRoomId()).get(selection));
            updateBalanceGUI();
            updateShopGUI(currentShopItems, selection);

            //Item bought - resetting
            for (int i = 0; i < 8; i++) {
                radioButtons.get(i).setSelected(false);
            };
            pointsavailable.setText("(Balance: " + Money.getMoney().toString() + "$)");
            shopitemlabel.setVisible(false);
            shoppricelabel.setVisible(false);
            shopBuyButton.setVisible(false);
            itemname.setText("You bought a:");
            itemprice.setStyle("-fx-font-weight: bold");
            itemprice.setText(currentShopItems.get(selection).getType());

        }
    }
    public int getShopSelectedItem(ArrayList<Item> items, ArrayList<RadioButton> radioButtons){
        int selection = 0;
        for (int i = 0; i < items.size(); i++) {
            if(radioButtons.get(i).isSelected()){
                selection = i;
            }
        }
        return selection;
    }
    public void updateShopGUI(ArrayList<Item> currentShopItems, int selection){
        if(currentShopItems.get(selection).getPrice() > Money.getMoney()){
            shopBuyButton.backgroundProperty().set(new Background(new BackgroundFill(Color.RED,null, null)));
        } else {
            shopBuyButton.backgroundProperty().set(new Background(new BackgroundFill(Color.GREEN,null, null)));
        }
    }
    public void onShopCloseButtonClick(){
        disableControls = false;
        shopSubScene.getParent().getParent().setVisible(false);
        ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
        ArrayList<Pane> slots = new ArrayList<>(List.of(shopslot1, shopslot2, shopslot3, shopslot4, shopslot5, shopslot6, shopslot7, shopslot8));
        ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));

        for (int i = 0; i < 8; i++) {
            slots.get(i).setBackground(null);
            radioButtons.get(i).setSelected(false);
            radioButtons.get(i).setVisible(false);
        }
        shopitemlabel.setVisible(false);
        shoppricelabel.setVisible(false);
        itemprice.setVisible(false);
        shopBuyButton.setVisible(false);
        itemprice.setStyle("-fx-font-weight: normal");
        itemname.setText("Please select an item...");
    }
}