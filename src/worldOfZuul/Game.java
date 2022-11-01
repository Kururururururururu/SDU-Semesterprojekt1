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
        Room hub, district_1, district_2, district_3, coal_power_plant;
        // coal_power_plant = new Room(" in the area of the coal power plant");
        hub.setExit("S", district_3);
        hub.setExit("E", district_1);
        hub.setExit("SE", district_2);

        district_1.setExit("W", hub);
        //district_1.setExit("E", coal_power_plant);
        district_1.setExit("S", district_2);

        district_2.setExit("N", district_1);
        district_2.setExit("NW", hub);
        district_2.setExit("W", district_3);

        district_3.setExit("E", district_2);
        district_3.setExit("N", hub);

        // coal_power_plant.setExit("West", district_1);
        currentRoom = hub;
    }

    public void printMap(){
        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
//        ┌─┐│└─┘●   ↖ ↘    ↓↑←──────────→
        System.out.println("┌────────────────────────┐");
        if (getRoomId()==0){
            System.out.println("│     "
                    + ANSI_RED +"HUB"+ ANSI_RESET +
                    "     Dirty Hills│");
            System.out.println("│      "
                    + ANSI_RED +"●"+ ANSI_RESET +
                    "  ← ─ ─ →  ●     │");
        } else if (getRoomId()==1) {
            System.out.println("│     HUB     "
                    + ANSI_RED +"Dirty Hills"+ ANSI_RESET +
                    "│");
            System.out.println("│      ●  ← ─ ─ →  "
                    + ANSI_RED +"●"+ ANSI_RESET +
                    "     │");
        } else {
            System.out.println("│     HUB     Dirty Hills│");
            System.out.println("│      ●  ← ─ ─ →  ●     │");
        }
        System.out.println("│      ↑  ↖        ↑     │");
        System.out.println("│      |    ╲      |     │");
        System.out.println("│      |      ╲    |     │");
        System.out.println("│      ↓        ↘  ↓     │");
        if (getRoomId()==3){
            System.out.println("│      "
                    + ANSI_RED +"●"+ ANSI_RESET +
                    "  ← ─ ─ →  ●     │");
            System.out.println("│"
                    + ANSI_RED +"Solar City"+ ANSI_RESET +
                    "    Windy Town│");
        } else if (getRoomId()==2) {
            System.out.println("│      ●  ← ─ ─ →  "
                    + ANSI_RED +"●"+ ANSI_RESET +
                    "     │");
            System.out.println("│Solar City    "
                    + ANSI_RED +"Windy Town"+ ANSI_RESET +
                    "│");
        } else {
            System.out.println("│      ●  ← ─ ─ →  ●     │");
            System.out.println("│Solar City    Windy Town│");
        }
        System.out.println("└────────────────────────┘");
//        System.out.println(getRoomId());
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

    public String getRoomDescription() {
        return currentRoom.getLongDescription();
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

    public String get_curr_id() {
        return currentRoom.getid();
    }

}
