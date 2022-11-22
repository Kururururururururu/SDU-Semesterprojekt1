package worldOfZuul;

import java.util.List;
import java.io.*;

public class Game {

    private Room currentRoom;
    private CommandWords commands;

    public Game() {
        createRooms();
        commands = new CommandWordsImplementation();
    }

    private void createRooms() {
        // Room outside, theatre, pub, lab, office;
        Room hub, F, B, A, C, G, E, D;

        hub = new Room("standing in the hub (main spawn area)", 0);
        F = new Room("standing in Dirty Hills", 1);
        B = new Room("standing in Windy Town", 2);
        A = new Room("standing in Solar City", 3);
        C = new Room("Standing in C", 4);
        G = new Room("Standing in G", 5);
        E = new Room("Standing in E", 6);
        D = new Room("Standing in D", 7);


        hub.setExit("E", F);
        hub.setExit("SE", B);
        hub.setExit("S", A);

        F.setExit("E", G);
        F.setExit("S", B);
        F.setExit("W", hub);

        B.setExit("N", F);
        B.setExit("SE", E);
        B.setExit("SW", C);
        B.setExit("W", A);
        B.setExit("NW", hub);

        A.setExit("N", hub);
        A.setExit("E", B);
        A.setExit("S", C);

        C.setExit("N", A);
        C.setExit("NE", B);
        C.setExit("E", D);

        G.setExit("SW", B);
        G.setExit("W", F);

        E.setExit("NW", B);

        D.setExit("N", B);
        D.setExit("W", C);



        currentRoom = hub;
    }

    public boolean goRoom(Command command) {

        if (!command.hasCommandValue()) {
            //No direction on command.
            //Can't continue with GO command.
            return false;
        }

        String direction = command.getCommandValue();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            return false;
        } else {
            currentRoom = nextRoom;
            currentRoom.runEnvironment();
            return true;
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
