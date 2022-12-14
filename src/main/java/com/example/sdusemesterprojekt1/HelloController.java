package com.example.sdusemesterprojekt1;

import EventColliders.Collider;
import EventColliders.RoomchangeCollider;
import EventColliders.SolidCollider;
import Misc.Item;
import Misc.Money;
import Misc.Points;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import worldOfZuul.Game;
import worldOfZuul.Room;
import java.io.IOException;
import java.net.URL;
import java.util.*;


public class HelloController implements Initializable {
    private static Game game = new Game();
    private boolean mapOpenStatus = false;
    private boolean helpOpenStatus = false;
    private static boolean disableControls = false;
    private static ArrayList<Collider> colliders = new ArrayList<>();
    private ArrayList<Pane> installLocation = new ArrayList<>();
    private ArrayList<Pane> installLocationBig = new ArrayList<>();
    private static ArrayList<Node> roomchangecolliders = new ArrayList<>();
    private final Timer removeBubbles = new Timer();
    private ArrayList<Pane> npc = new ArrayList<>();
    @FXML
    private GridPane background;
    @FXML
    private Pane player, menugui, mapUnfold, helpUnfold;
    @FXML
    private Pane slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8, shopslot1, shopslot2, shopslot3, shopslot4, shopslot5, shopslot6, shopslot7, shopslot8;
    @FXML
    private Label helpUnfoldText, balance, gopoints, gobalance, textbubble, textbubble2;
    @FXML
    private Label slot1label, slot2label, slot3label, slot4label, slot5label, slot6label, slot7label, slot8label;
    @FXML
    private Label shopname, shopitemlabel, shoppricelabel, itemname, itemprice, pointsavailable;
    @FXML
    private AnchorPane gameover;
    @FXML
    private Hyperlink golink;
    @FXML
    private Button goplay, goexit, shopBuyButton,exitbtn, continuebtn;
    @FXML
    private RadioButton item1, item2, item3, item4, item5, item6, item7, item8;
    @FXML
    private AnchorPane inventorySubScene, shopSubScene;
    @FXML
    private Tooltip slot1tooltip, slot2tooltip, slot3tooltip, slot4tooltip, slot5tooltip, slot6tooltip, slot7tooltip, slot8tooltip;
    private boolean is_menu_open = false;
    int responseCount = 0;
    int index_of_response;
    String lastResponse = "UNDEFINED";

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
    private void displaytextbubble(Pane npc) throws IOException {

        String current_npc_in_room = npc.toString().substring(8, npc.toString().length() - 1);
        //System.out.println(current_npc_in_room);
        switch (current_npc_in_room) {
            case "boss_npc":
                ArrayList<String> responses = new ArrayList<String>(List.of("" +
                                "Try putting down some green energy. The villagers will LOVE it!",
                        "Solar or wind? Doesn't matter, just change it from coal.",
                        "Some of the villagers may be stubborn to the old days of coal.",
                        "Our goal is for the greater good!",
                        "A lot of these people still rely on inefficient and polluting coal systems. Sad",
                        "We can all agree that everyone should have clean and affordable energy - right?",
                        "If we keep increasing solar and wind, we'll be on track for our goal!",
                        "Our goal is clean and affordable energy for everyone by 2030!",
                        "Go back to the coast when you're done for today!",
                        "Your voyage home is by the coast",
                        "Go to the coast when you are done!"));
                index_of_response = (int)(Math.random() * responses.size());
                while (responses.get(index_of_response) == lastResponse) {
                    index_of_response = (int)(Math.random() * responses.size());
                }
                lastResponse = responses.get(index_of_response);
                textbubble.setText(responses.get(index_of_response));
                textbubble.setStyle("-fx-opacity: 100%");
                removeBubbles.schedule(
                        new java.util.TimerTask() {
                            public void run() {
                                while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                }
                                {
                                    textbubble.setStyle("-fx-opacity: 0%");
                                }

                            }
                        },
                        5000
                );
                break;
            case "skipper_npc":
                if (game.getIsClean()) {
                    responses = new ArrayList<String>(List.of("" +
                                    "Wow, you really did it!",
                            "You installed green, sustainable energy sources for all the villagers.",
                            "Well done!",
                            "Let's sail into the sunset together.",
                            ""
                    ));
                    textbubble.setText(responses.get(responseCount));
                    textbubble.setStyle("-fx-opacity: 100%");
                    responseCount+=1;
                    //System.out.println("response count = "+responseCount);
                    removeBubbles.schedule(
                            new java.util.TimerTask() {
                                public void run() {
                                    while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                    }
                                    {
                                        textbubble.setStyle("-fx-opacity: 0%");
                                    }

                                }
                            },
                            5000
                    );
                    if (responseCount == responses.size()) {
                        game.setGameOver();
                    }
                }
                else {
                    responses = new ArrayList<String>(List.of("" +
                                    "Oh you, you're finally awake? Welcome to Green Watts",
                            "Have you completed all your tasks for today?",
                            "It's inspiring to watch you work.",
                            "You will surely make an impact around here.",
                            "Yarrrr matey.",
                            "Come back and talk to me, when you've cleaned this filth!",
                            "I'll be waiting for you here!"));
                    index_of_response = (int)(Math.random() * responses.size());
                    while (responses.get(index_of_response) == lastResponse) {
                        index_of_response = (int)(Math.random() * responses.size());
                    }
                    lastResponse = responses.get(index_of_response);
                    textbubble.setText(responses.get(index_of_response));
                    textbubble.setStyle("-fx-opacity: 100%");
                    removeBubbles.schedule(
                            new java.util.TimerTask() {
                                public void run() {
                                    while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                    }
                                    {
                                        textbubble.setStyle("-fx-opacity: 0%");
                                    }

                                }
                            },
                            5000
                    );
                }
                break;
            case "coal_npc":
                if(game.getIsClean()){
                    responses = new ArrayList<String>(List.of("" +
                            "Green energy will always be more reliable!",
                            "Thanks for convincing me.",
                            "It's the start of a new era!",
                            "Fix what's broken!",
                            "I love the green gold.",
                            "Green is better - you changed my mind!",
                            "Go to the coast now, it's been my pleasure!",
                            "What a wonderfull day to go sailing!"));
                    index_of_response = (int) (Math.random() * responses.size());
                    while (responses.get(index_of_response) == lastResponse) {
                        index_of_response = (int) (Math.random() * responses.size());
                    }
                    lastResponse = responses.get(index_of_response);
                    textbubble.setText(responses.get(index_of_response));
                    textbubble.setStyle("-fx-opacity: 100%");
                    removeBubbles.schedule(
                            new java.util.TimerTask() {
                                public void run() {
                                    while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                    }
                                    {
                                        textbubble.setStyle("-fx-opacity: 0%");
                                    }

                                }
                            },
                            5000
                    );
                }else {
                    responses = new ArrayList<String>(List.of("" +
                                    "Coal will always be more reliable!",
                            "I don't see green energy making me this much money.",
                            "It's the way we've done it for decades.",
                            "Don't fix what ain't broken!",
                            "I love the black gold.",
                            "Coal is better - Change my mind!"));
                    index_of_response = (int) (Math.random() * responses.size());
                    while (responses.get(index_of_response) == lastResponse) {
                        index_of_response = (int) (Math.random() * responses.size());
                    }
                    lastResponse = responses.get(index_of_response);
                    textbubble.setText(responses.get(index_of_response));
                    textbubble.setStyle("-fx-opacity: 100%");
                    removeBubbles.schedule(
                            new java.util.TimerTask() {
                                public void run() {
                                    while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                    }
                                    {
                                        textbubble.setStyle("-fx-opacity: 0%");
                                    }

                                }
                            },
                            5000
                    );
                }
                break;
            case "npc5":
                //This is solar city
                responses = new ArrayList<String>(List.of("" +
                        "Solar energy has always been my favorite!",
                        "I DON'T LIKE COAL! It's bad for everyone!",
                        "I used to like coal, until i discovered the joys of solar energy!",
                        "Have you heard they still only wanna use coal at dirty hills!? You should go change that!"));
                index_of_response = (int)(Math.random() * responses.size());
                while (responses.get(index_of_response) == lastResponse) {
                    index_of_response = (int)(Math.random() * responses.size());
                }
                lastResponse = responses.get(index_of_response);
                textbubble.setText(responses.get(index_of_response));
                textbubble.setStyle("-fx-opacity: 100%");
                removeBubbles.schedule(
                        new java.util.TimerTask() {
                            public void run() {
                                while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                }
                                {
                                    textbubble.setStyle("-fx-opacity: 0%");
                                }

                            }
                        },
                        5000
                );
                break;
            case "npc2":
                //Windy town
                responses = new ArrayList<String>(List.of("" +
                                "Nice to meet you, my friend!",
                        "It sure is windy today!",
                        "Perfect wind for some clean green wind energy!",
                        "Perfect day to use a kite!",
                        "You should go teach them a windy lesson at Dirty Hills!"));
                index_of_response = (int)(Math.random() * responses.size());
                while (responses.get(index_of_response) == lastResponse) {
                    index_of_response = (int)(Math.random() * responses.size());
                }
                lastResponse = responses.get(index_of_response);
                textbubble.setText(responses.get(index_of_response));
                textbubble.setStyle("-fx-opacity: 100%");
                removeBubbles.schedule(
                        new java.util.TimerTask() {
                            public void run() {
                                while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                }
                                {
                                    textbubble.setStyle("-fx-opacity: 0%");
                                }

                            }
                        },
                        5000
                );
                break;
            case "npc4":
                responses = new ArrayList<String>(List.of("" + "We have no other options than to use coal currently. It's sad.",
                        "Wish we could have some of those fancy green energy. Maybe solar panels?",
                        "Are you new around here? We're used to coal sadly.",
                        "I'll be willing to try something new, if it's good!",
                        "I'm so tired of all this black coal",
                        "We can't keep living in this filth!"));
                index_of_response = (int)(Math.random() * responses.size());
                while (responses.get(index_of_response) == lastResponse) {
                    index_of_response = (int)(Math.random() * responses.size());
                }
                lastResponse = responses.get(index_of_response);
                textbubble.setText(responses.get(index_of_response));
                textbubble.setStyle("-fx-opacity: 100%");
                removeBubbles.schedule(
                        new java.util.TimerTask() {
                            public void run() {
                                while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                }
                                {
                                    textbubble.setStyle("-fx-opacity: 0%");
                                }

                            }
                        },
                        5000
                );
                break;
            case "npc1":
                responses = new ArrayList<String>(List.of("" +
                        "I just want a better future for my kids.",
                        "I'm so tired of only seeing black coal in this city",
                        "What a filthy place we've become",
                        "We need something new around here"));
                index_of_response = (int)(Math.random() * responses.size());
                while (responses.get(index_of_response) == lastResponse) {
                    index_of_response = (int)(Math.random() * responses.size());
                }
                lastResponse = responses.get(index_of_response);
                textbubble2.setText(responses.get(index_of_response));
                textbubble2.setStyle("-fx-opacity: 100%");
                removeBubbles.schedule(
                        new java.util.TimerTask() {
                            public void run() {
                                while (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                                }
                                {
                                    textbubble2.setStyle("-fx-opacity: 0%");
                                }

                            }
                        },
                        5000
                );
                break;
            default:
                System.out.print("ERROR: no response found!");
                break;
        }
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
        installLocation = new ArrayList<>();
        installLocationBig = new ArrayList<>();
        npc = new ArrayList<>();
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
                    roomchangecolliders.add(child);
                }
                if(child.getId().equals("installlocation") && child.getClass() ==  Pane.class)   {
                    //System.out.println("Roomchange collider found");
                    installLocation.add((Pane) child);
                }
                if(child.getId().equals("installlocationBig") && child.getClass() ==  Pane.class)   {
                    //System.out.println("Roomchange collider found");
                    installLocationBig.add((Pane) child);
                }
                if(child.getAccessibleText() != null && child.getAccessibleText().equals("npc")) {
                    //System.out.println("Npc found");
                    npc.add((Pane) child);
                }
            }
        }
    }

    public void renderRoomItems(Room room)   {
        //System.out.println("Ran render" + "big: " + installLocationBig.size() + " | small: " + installLocation.size());
        int x = 0;
        int i = 0;
        int j = 0;
        if(room.getItems().size() > 0) {
                for (Item item : room.getItems()) {
                    //System.out.println(item.getName());
                    if(room.getItems().get(x).getType() == "SOLARPANEL") {
                        String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + room.getItems().get(x).getName().replaceAll("\\s+", "") + "16x16.png";
                        //System.out.println(iconPath);
                        BackgroundImage icon = new BackgroundImage(new Image(iconPath, 32, 32, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                        installLocation.get(i).setBackground(new Background(icon));
                        i++;
                    } else {
                        String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + room.getItems().get(x).getName().replaceAll("\\s+", "") + "32x64.png";
                        //System.out.println(iconPath);
                        BackgroundImage icon = new BackgroundImage(new Image(iconPath, 64, 128, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                        installLocationBig.get(j).setBackground(new Background(icon));
                        j++;
                    }
                    x++;
                }



        }
        updateBalanceGUI();
    }

    //onClick calls from FXML
    @FXML
    public void onBagButtonClick() {
        if(!isPaused()){
            disableControls = true;
            ArrayList<Pane> slots = new ArrayList<>(List.of(slot1, slot2, slot3, slot4, slot5, slot6, slot7, slot8));
            ArrayList<Label> slotlabels = new ArrayList<>(List.of(slot1label, slot2label, slot3label, slot4label, slot5label, slot6label, slot7label, slot8label));
            ArrayList<Tooltip> slottooltips = new ArrayList<>(List.of(slot1tooltip, slot2tooltip, slot3tooltip, slot4tooltip, slot5tooltip, slot6tooltip, slot7tooltip, slot8tooltip));

            ArrayList<Item> inv = game.getMainCharacter().getPlayer_inventory().getInventoryUniques();
            ArrayList<Integer> item_count = game.getMainCharacter().getPlayer_inventory().getInventoryUniquesCount();

            int slot = 0;
            for (Item i: inv) {
                String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + "Inventory-" + i.getName().replaceAll("\\s+","") + "16x16.png";
                BackgroundImage icon = new BackgroundImage(new Image( iconPath,48,48,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                slots.get(slot).setBackground(new Background(icon));
                slottooltips.get(slot).setOpacity(1);
                slottooltips.get(slot).setText(i.getName());
                slotlabels.get(slot).setText("x"+item_count.get(slot).toString());

                // For debugging
                //System.out.println("*******************\nSlot: " + (slot+1) + "\nItem name: " +i.getName() + "\nItem count: " + item_count.get(slot) + "\nIcon url: " + iconPath + "\n*******************\n");

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

    @FXML
    public void onInventorySlotClick(MouseEvent event) {
        //System.out.println(event.getTarget());
        int slotIndex = Integer.parseInt(event.getTarget().toString().substring(event.getTarget().toString().length() - 2, event.getTarget().toString().length() - 1));
        //TODO add visual indicator for false return.
        //System.out.println("ran");
        game.getMainCharacter().useItem(slotIndex-1, game.getRoom());
    }

    @FXML
    public void onMapButtonClick() {
        if (!isPaused()) {
            //System.out.println("Opening Map");
            disableControls = true;
            mapUnfold.setVisible(true);
            mapOpenStatus = true;
        }
        else if (mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible()) {
            //System.out.println("Closing Map");
            disableControls = false;
            mapUnfold.setVisible(false);
            mapOpenStatus = false;
        }
    }
    @FXML
    public void onMagButtonClick() {
        //System.out.println("Magnifying Glass");
    }
    @FXML
    public void onHandButtonClick() {
        //System.out.println("Hand");
        openShop();
        //System.out.println("Room id:" + game.getRoomId());
    }



    @FXML
    public void onTalkButtonClick() throws IOException {
        //System.out.println("Talk");
        if(!isPaused()){
            npcTalk();
        }
    }
    @FXML
    public void onGameOver() {
        gobalance.setText(Money.getMoney().toString());
        gopoints.setText(Points.getPoints().toString());
        goplay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println("Restart game.");
                game.showScene("coast");
            }
        });
        goexit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                //System.out.println("Exit game.");
                System.exit(0);
            }
        });
        golink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //System.out.println("link clicked");
            }
        });
    }
    @FXML
    public void onMenuButtonClick() {
        if (is_menu_open == false) {
            is_menu_open = true;
            disableControls = true;
            menugui.setMouseTransparent(false);
            menugui.setVisible(true);
            menugui.setStyle("-fx-opacity: 100%");
            menugui.setStyle("-fx-background-color: white;");
            continuebtn.setStyle("-fx-opacity: 100%");
            exitbtn.setStyle("-fx-opacity: 100%");
            continuebtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    onMenuButtonClick();
                }
            });
            exitbtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent e) {
                    System.exit(0);
                }
            });

        } else {
            is_menu_open = false;
            menugui.setMouseTransparent(true);
            menugui.setVisible(false);
            menugui.setStyle("-fx-opacity: 0%");
            menugui.setStyle("-fx-background-color: transparent;");
            continuebtn.setStyle("-fx-opacity: 0%");
            exitbtn.setStyle("-fx-opacity: 0%");
            if(!isPaused()){
                disableControls = false;
            }
        }
    }

    @FXML
    public void onHelpButtonClick() {
        if (!isPaused()) {
            //System.out.println("Opening Help");
            disableControls = true;
            helpUnfold.setVisible(true);
            helpUnfoldText.setVisible(true);
            helpOpenStatus = true;
        }
        else if (helpOpenStatus && !inventorySubScene.getParent().getParent().isVisible()) {
            //System.out.println("Closing Help");
            disableControls = false;
            helpUnfold.setVisible(false);
            helpUnfoldText.setVisible(false);
            helpOpenStatus = false;
        }

    }
    //End onClick calls from FXML



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
    public void npcTalk() throws IOException {
        for (Pane npc:npc) {
            if (npcIsTalkable(getBackground(), getPlayer(), npc)) {
                //System.out.println("HELLO I CAN TALK");
                displaytextbubble(npc);
            }
        }
    }
    @FXML
    public void openShop() {
        if((game.getRoomId().equals(0) || game.getRoomId().equals(1) ) && npcIsTalkable(getBackground(), getPlayer(), npc.get(0)) && game.getIsClean() == false){
            if(!isPaused()) {
                disableControls = true;
                shopSubScene.getParent().getParent().setVisible(true);

                //System.out.println("Shop opens");
                ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
                ArrayList<Pane> slots = new ArrayList<>(List.of(shopslot1, shopslot2, shopslot3, shopslot4, shopslot5, shopslot6, shopslot7, shopslot8));
                ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));

                pointsavailable.setText("(Balance: " + Money.getMoney().toString() + "$)");

                for (int i = 0; i < currentShopItems.size(); i++) {
                    String iconPath = HelloApplication.class.getClassLoader().getResource("icons/") + "Inventory-" + currentShopItems.get(i).getName().replaceAll("\\s+", "") + "16x16.png";
                    BackgroundImage icon = new BackgroundImage(new Image(iconPath, 48, 48, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
                    slots.get(i).setBackground(new Background(icon));

                    radioButtons.get(i).setVisible(true);

                    //System.out.println(currentShopItems.get(i).getName());

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

        itemname.setText(currentShopItems.get(selection).getName());
        itemprice.setText(currentShopItems.get(selection).getPrice().toString() + "$");
        pointsavailable.setText("(Balance: " + Money.getMoney().toString() + "$)");

        updateShopGUI(currentShopItems, selection);
    }
    public void onShopBuyButtonClick() throws InterruptedException {
        ArrayList<Item> currentShopItems = game.getPointShop().currentShop(game.getRoomId());
        ArrayList<RadioButton> radioButtons = new ArrayList<>(List.of(item1, item2, item3, item4, item5, item6, item7, item8));
        int selection = getShopSelectedItem(currentShopItems, radioButtons);
        if(currentShopItems.get(selection).getPrice() > Money.getMoney()){
            //System.out.println("Not enough $");
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
            itemprice.setText(currentShopItems.get(selection).getName());

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


    public boolean isPaused(){
        if(shopSubScene != null) {
            if (!mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible() && !helpOpenStatus && !shopSubScene.getParent().getParent().isVisible() && !is_menu_open) {
                return false;
            } else {
                return true;
            }
        } else {
            if (!mapOpenStatus && !inventorySubScene.getParent().getParent().isVisible() && !helpOpenStatus && !is_menu_open) {
                return false;
            } else {
                return true;
            }
        }
    }

    public GridPane getBackground() {
        return this.background;
    }

    public Pane getPlayer() {
        return this.player;
    }

    public ArrayList<Pane> getNPC() {return this.npc; }

    public void isCleanDirtyHills(boolean bool) {
        game.setIsClean(bool);
    }

    public ArrayList<Node> getRoomchangecolliders(){
        return this.roomchangecolliders;
    }
}