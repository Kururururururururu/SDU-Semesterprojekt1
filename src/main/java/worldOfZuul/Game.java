package worldOfZuul;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

import Characters.MainCharacter;
import EventColliders.RoomchangeCollider;
import Misc.Item;
import Misc.Money;
import com.example.sdusemesterprojekt1.HelloApplication;
import com.example.sdusemesterprojekt1.HelloController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game {

    private Room currentRoom;
    private CommandWords commands;
    private static Stage gameStage;
    private HelloController controller = new HelloController(this);

    MainCharacter mainCharacter = new MainCharacter("John");
    Item SmallSolarPanel = new Item("Small Solar Panel", 100, 1);
    Item HugeSolarPanel = new Item("Huge Solar Panel", 1500, 2);
    Item WoodenWindTurbine = new Item("Wooden Wind Turbine", 200, 3);
    Item IndustrialWindTurbine = new Item("Industrial Wind Turbine", 2500, 4);

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        // Room outside, theatre, pub, lab, office;
        Room hub, dh, wt, sc, c, no;

        hub = new Room("Hub", 0);
        dh = new Room("Dirty Hills", 1);
        wt = new Room("Windy Town", 2);
        sc = new Room("Solar City", 3);
        c = new Room("Coast", 4);
        no = new Room("New Orleans", 5);


        // initialise room exits
        hub.setExit("W",c);
        hub.setExit("E",dh);
        hub.setExit("S",sc);

        c.setExit("E",hub);

        dh.setExit("W",hub);
        dh.setExit("S",sc);

        sc.setExit("N",hub);
        sc.setExit("E",dh);
        sc.setExit("S",no);
        sc.setExit("W",wt);

        wt.setExit("N",sc);
        wt.setExit("E",no);

        no.setExit("N",sc);
        no.setExit("W",wt);

        currentRoom = c;
    }

    public boolean goRoom(String direction) {

        if (direction == null) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom.setLastExit(direction);
            currentRoom = nextRoom;
            showScene(currentRoom.getDescription().toLowerCase().replaceAll("\s+",""));
            currentRoom.runEnvironment();
            return true;
        }
    }

    public void showScene(String sceneName)  {
        try {
            if(gameStage == null) {
                gameStage = new Stage();
                gameStage.setTitle("Green Watts");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(sceneName + ".fxml"));
            fxmlLoader.setController(controller);
            System.out.println("Loading scene: " + sceneName);
            Scene scene = new Scene(fxmlLoader.load());
            gameStage.setScene(scene);
            scene.setOnKeyPressed(event -> {
                try {
                    keypressHandler(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            gameStage.show();
            controller.checkColliders();
            controller.updateBalanceGUI();
            correctRoomchangeEntrance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showSubScene(String subSceneName, BorderPane subScene) {
    }


    private void keypressHandler(KeyEvent event) throws IOException {
        //Handle all keypresses here.
        switch (event.getCode())  {
            case W -> { HelloController.movePlayer("up", controller.getBackground(), controller.getPlayer()); }
            case S -> { HelloController.movePlayer("down", controller.getBackground(), controller.getPlayer()); }
            case A -> { HelloController.movePlayer("left", controller.getBackground(), controller.getPlayer()); }
            case D -> { HelloController.movePlayer("right", controller.getBackground(), controller.getPlayer()); }
            case I -> { controller.onBagButtonClick(); }
            case M -> { controller.onMapButtonClick(); }
            case E -> { controller.onMagButtonClick(); }
            case F -> { controller.onHandButtonClick(); }
            case T -> { controller.onTalkButtonClick(); }
            case ESCAPE -> { controller.onMenuButtonClick(); }
            case H -> { controller.onHelpButtonClick(); }
            case F5 -> {mainCharacter.addToInventory(SmallSolarPanel);}
            case F6 -> {mainCharacter.addToInventory(HugeSolarPanel);}
            case F7 -> {mainCharacter.addToInventory(WoodenWindTurbine);}
            case F8 -> {mainCharacter.addToInventory(IndustrialWindTurbine);}
            case F2 -> {
                Money.addMoney(100);
                controller.updateBalanceGUI();
            }
        }
    }
    public void correctRoomchangeEntrance(){
        if(currentRoom.getLastExit() != null) {
            switch (currentRoom.getLastExit()) {
                case "N" -> {
                    ArrayList<Node> roomchangeColliders =  controller.getRoomchangecolliders();
                    int countEntrances = 0;
                    for (Node node : roomchangeColliders) {
                        if(node.getAccessibleText().equals("S")){
                            controller.getBackground().setRowIndex(controller.getPlayer(), controller.getBackground().getRowIndex(node));
                            controller.getBackground().setColumnIndex(controller.getPlayer(), controller.getBackground().getColumnIndex(node));
                            countEntrances++;
                        }
                    }
                    System.out.println(countEntrances);
                }
                case "S" -> {
                    ArrayList<Node> roomchangeColliders =  controller.getRoomchangecolliders();
                    int countEntrances = 0;
                    for (Node node : roomchangeColliders) {
                        if(node.getAccessibleText().equals("N")){
                            controller.getBackground().setRowIndex(controller.getPlayer(), controller.getBackground().getRowIndex(node));
                            controller.getBackground().setColumnIndex(controller.getPlayer(), controller.getBackground().getColumnIndex(node));
                            countEntrances++;
                        }
                    }
                    System.out.println(countEntrances);
                }
                case "W" -> {
                    ArrayList<Node> roomchangeColliders =  controller.getRoomchangecolliders();
                    int countEntrances = 0;
                    for (Node node : roomchangeColliders) {
                        if(node.getAccessibleText().equals("E")){
                            controller.getBackground().setRowIndex(controller.getPlayer(), controller.getBackground().getRowIndex(node));
                            controller.getBackground().setColumnIndex(controller.getPlayer(), controller.getBackground().getColumnIndex(node));
                            countEntrances++;
                        }
                    }
                    System.out.println(countEntrances);
                }
                case "E" -> {
                    ArrayList<Node> roomchangeColliders =  controller.getRoomchangecolliders();
                    int countEntrances = 0;
                    for (Node node : roomchangeColliders) {
                        if(node.getAccessibleText().equals("W")){
                            controller.getBackground().setRowIndex(controller.getPlayer(), controller.getBackground().getRowIndex(node));
                            controller.getBackground().setColumnIndex(controller.getPlayer(), controller.getBackground().getColumnIndex(node));
                            countEntrances++;
                        }
                    }
                    System.out.println(countEntrances);
                }
            }
        }
    }


    public boolean quit(Command command) {
        if (command.hasCommandValue()) {
            return false;
        } else {
            return true;
        }
    }

    public String getRoomName() {
        return currentRoom.getDescription();
    }

    public CommandWords getCommands() {
        return commands;
    }

    public List<String> getCommandDescriptions() {
        return commands.getCommandWords();
    }

    public Command getCommand(String word1, String word2) {
        return new CommandImplementation(commands.getCommand(word1), word2);
    }
    public Integer getRoomId() {
        return currentRoom.getRoomId();
    }
    public Room getRoom() {return currentRoom;}

    public String get_curr_id() {
        return currentRoom.getRoomId().toString();
    }

    public MainCharacter getMainCharacter() { return this.mainCharacter;}

}
