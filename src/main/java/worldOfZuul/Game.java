package worldOfZuul;

import java.util.List;
import java.io.*;

import Characters.MainCharacter;
import Misc.Money;
import com.example.sdusemesterprojekt1.HelloApplication;
import com.example.sdusemesterprojekt1.HelloController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Game {

    private Room currentRoom;
    private CommandWords commands;
    private static Stage gameStage;

    private HelloController controller = new HelloController(this);

    MainCharacter mainCharacter = new MainCharacter("John");

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

        currentRoom = hub;
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
            scene.setOnKeyPressed(event -> keypressHandler(event));
            gameStage.show();
            controller.checkColliders();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void keypressHandler(KeyEvent event) {
        //Handle all keypresses here.
        switch (event.getCode())  {
            case W -> { HelloController.movePlayer("up", controller.getBackground(), controller.getPlayer()); }
            case S -> { HelloController.movePlayer("down", controller.getBackground(), controller.getPlayer()); }
            case A -> { HelloController.movePlayer("left", controller.getBackground(), controller.getPlayer()); }
            case D -> { HelloController.movePlayer("right", controller.getBackground(), controller.getPlayer()); }
            case I -> { controller.onBagButtonClick(); }
            case M -> { controller.onMapButtonClick(); }
            case G -> { controller.onMagButtonClick(); }
            case H -> { controller.onHandButtonClick(); }
            case T -> { controller.onTalkButtonClick(); }
            case ESCAPE -> { controller.onMenuButtonClick(); }
            case F1 -> { controller.onHelpButtonClick(); }
            case F2 -> { Money.addMoney(100); }
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
