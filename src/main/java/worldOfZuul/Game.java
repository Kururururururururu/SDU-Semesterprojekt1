package worldOfZuul;

import java.util.List;
import java.io.*;
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

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        // Room outside, theatre, pub, lab, office;
        Room hub, F, B, A, C, G, E, D;

        hub = new Room("Hub", 0);
        F = new Room("Dirty Hills", 1);
        B = new Room("Windy Town", 2);
        A = new Room("Solar City", 3);
        C = new Room("Coast", 4);
        G = new Room("New Orleans", 5);


        hub.setExit("E", F);
        hub.setExit("SE", B);
        hub.setExit("S", A);

        F.setExit("E", G);
        F.setExit("S", B);
        F.setExit("W", hub);

        B.setExit("N", F);
        B.setExit("SW", C);
        B.setExit("W", A);
        B.setExit("NW", hub);

        A.setExit("N", hub);
        A.setExit("E", B);
        A.setExit("S", C);

        C.setExit("N", A);
        C.setExit("NE", B);

        G.setExit("SW", B);
        G.setExit("W", F);

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
            showScene(currentRoom.getDescription());
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
            Scene scene = new Scene(fxmlLoader.load());
            gameStage.setScene(scene);
            scene.setOnKeyPressed(event -> keypressHandler(event));
            gameStage.show();
            controller.checkColliders(); // TODO Test this function
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
            case F2 -> { showScene("coast"); }
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

}
