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
    public void printMap(){
//        ANSI Colors
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";
        String YELLOW = "\u001B[33m";
//        ┌ ─ ┐ │ └ ─ ┘ ┬ ┴ ├ ┤ ● ↖ ↘ ↗ ↙ ↓ | ↑ ← ─ →
//        System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
//        System.out.println("│     HUB            f             g  │     HUB          │");
//        System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
//        System.out.println("│      ↑  ↖          ↑          /     │ b - Windy Town   │");
//        System.out.println("│      |     ╲       |       /        │ c - City C       │");
//        System.out.println("│      |       ╲     |    ↙           │ d - City D       │");
//        System.out.println("│      ↓          ↘  ↓                │ e - City E       ├─────────────────────┐");
//        System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
//        System.out.println("│      ↑          ↗     ↖             │ g - City G       │     NW   │   NE     │");
//        System.out.println("│      |       /     ↑     ╲          │                  │        ╲ │ /        │");
//        System.out.println("│      |     /       |       ╲        │                  │  W ───── ● ───── E  │");
//        System.out.println("│      ↓  ↙          |          ↘     │                  │        / │ ╲        │");
//        System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
//        System.out.println("│      c             d             e  │                  │          S          │");
//        System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
// -----------------------------------------
        switch (getRoomId()){
            case 0: //hub
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     "+RED+"HUB"+RESET+"            f             g  │     "+RED+"HUB"+RESET+"          │");
                System.out.println("│      "+RED+"●"+RESET+"  "+YELLOW+"← ── ── →"+RESET+"  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      "+YELLOW+"↑"+RESET+"  "+YELLOW+"↖"+RESET+"          ↑          /     │ b - Windy Town   │");
                System.out.println("│      "+YELLOW+"|"+RESET+"     "+YELLOW+"╲"+RESET+"       |       /        │ c - City C       │");
                System.out.println("│      "+YELLOW+"|"+RESET+"       "+YELLOW+"╲"+RESET+"     |    ↙           │ d - City D       │");
                System.out.println("│      "+YELLOW+"↓"+RESET+"          "+YELLOW+"↘"+RESET+"  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          ↗     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       /     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 1: //f
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            "+RED+"f"+RESET+"             g  │     HUB          │");
                System.out.println("│      ●  "+YELLOW+"← ── ── →"+RESET+"  "+RED+"●"+RESET+"  "+YELLOW+"← ── ── →"+RESET+"  ●  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          "+YELLOW+"↑"+RESET+"          /     │ b - Windy Town   │");
                System.out.println("│      |     ╲       "+YELLOW+"|"+RESET+"       /        │ c - City C       │");
                System.out.println("│      |       ╲     "+YELLOW+"|"+RESET+"    ↙           │ d - City D       │");
                System.out.println("│      ↓          ↘  "+YELLOW+"↓"+RESET+"                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ "+RED+"f - Dirty Hills"+RESET+"  │          N          │");
                System.out.println("│      ↑          ↗     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       /     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 2: //b
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      ↑  "+YELLOW+"↖"+RESET+"          "+YELLOW+"↑"+RESET+"          /     │ "+RED+"b - Windy Town"+RESET+"   │");
                System.out.println("│      |     "+YELLOW+"╲"+RESET+"       "+YELLOW+"|"+RESET+"       /        │ c - City C       │");
                System.out.println("│      |       "+YELLOW+"╲"+RESET+"     "+YELLOW+"|"+RESET+"    ↙           │ d - City D       │");
                System.out.println("│      ↓          "+YELLOW+"↘"+RESET+"  "+YELLOW+"↓"+RESET+"                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  "+YELLOW+"← ── ── →"+RESET+"  "+RED+"● b"+RESET+"              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          "+YELLOW+"↗"+RESET+"     "+YELLOW+"↖"+RESET+"             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       "+YELLOW+"/"+RESET+"     ↑     "+YELLOW+"╲"+RESET+"          │                  │        ╲ │ /        │");
                System.out.println("│      |     "+YELLOW+"/"+RESET+"       |       "+YELLOW+"╲"+RESET+"        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  "+YELLOW+"↙"+RESET+"          |          "+YELLOW+"↘"+RESET+"     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 3: //a
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ "+RED+"a - Solar City"+RESET+"   │");
                System.out.println("│      "+YELLOW+"↑"+RESET+"  ↖          ↑          /     │ b - Windy Town   │");
                System.out.println("│      "+YELLOW+"|"+RESET+"     ╲       |       /        │ c - City C       │");
                System.out.println("│      "+YELLOW+"|"+RESET+"       ╲     |    ↙           │ d - City D       │");
                System.out.println("│      "+YELLOW+"↓"+RESET+"          ↘  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    "+RED+"a ●"+RESET+"  "+YELLOW+"← ── ── →"+RESET+"  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      "+YELLOW+"↑"+RESET+"          ↗     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      "+YELLOW+"|"+RESET+"       /     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      "+YELLOW+"|"+RESET+"     /       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      "+YELLOW+"↓"+RESET+"  ↙          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 4: //c
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          ↑          /     │ b - Windy Town   │");
                System.out.println("│      |     ╲       |       /        │ "+RED+"c - City C"+RESET+"       │");
                System.out.println("│      |       ╲     |    ↙           │ d - City D       │");
                System.out.println("│      ↓          ↘  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      "+YELLOW+"↑"+RESET+"          "+YELLOW+"↗"+RESET+"     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      "+YELLOW+"|"+RESET+"       "+YELLOW+"/"+RESET+"     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      "+YELLOW+"|"+RESET+"     "+YELLOW+"/"+RESET+"       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      "+YELLOW+"↓"+RESET+"  "+YELLOW+"↙"+RESET+"          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      "+RED+"●"+RESET+"  "+YELLOW+"← ── ── →"+RESET+"  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      "+RED+"c"+RESET+"             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 5: //g
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             "+RED+"g"+RESET+"  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  "+YELLOW+"← ── ── →"+RESET+"  "+RED+"●"+RESET+"  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          ↑          "+YELLOW+"/"+RESET+"     │ b - Windy Town   │");
                System.out.println("│      |     ╲       |       "+YELLOW+"/"+RESET+"        │ c - City C       │");
                System.out.println("│      |       ╲     |    "+YELLOW+"↙"+RESET+"           │ d - City D       │");
                System.out.println("│      ↓          ↘  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          ↗     ↖             │ "+RED+"g - City G"+RESET+"       │     NW   │   NE     │");
                System.out.println("│      |       /     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 6: //e
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          ↑          /     │ b - Windy Town   │");
                System.out.println("│      |     ╲       |       /        │ c - City C       │");
                System.out.println("│      |       ╲     |    ↙           │ d - City D       │");
                System.out.println("│      ↓          ↘  ↓                │ "+RED+"e - City E"+RESET+"       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          ↗     "+YELLOW+"↖"+RESET+"             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       /     ↑     "+YELLOW+"╲"+RESET+"          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       |       "+YELLOW+"╲"+RESET+"        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          |          "+YELLOW+"↘"+RESET+"     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             "+RED+"●"+RESET+"  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             "+RED+"e"+RESET+"  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            case 7: //d
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          ↑          /     │ b - Windy Town   │");
                System.out.println("│      |     ╲       |       /        │ c - City C       │");
                System.out.println("│      |       ╲     |    ↙           │ "+RED+"d - City D"+RESET+"       │");
                System.out.println("│      ↓          ↘  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          ↗     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       /     "+YELLOW+"↑"+RESET+"     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       "+YELLOW+"|"+RESET+"       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          "+YELLOW+"|"+RESET+"          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  "+YELLOW+"← ── ── →"+RESET+"  "+RED+"●"+RESET+"             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             "+RED+"d"+RESET+"             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
            default:
                System.out.println("┌─────────────────────────────────────┬──────────────────┐");//
                System.out.println("│     HUB            f             g  │     HUB          │");
                System.out.println("│      ●  ← ── ── →  ●  ← ── ── →  ●  │ a - Solar City   │");
                System.out.println("│      ↑  ↖          ↑          /     │ b - Windy Town   │");
                System.out.println("│      |     ╲       |       /        │ c - City C       │");
                System.out.println("│      |       ╲     |    ↙           │ d - City D       │");
                System.out.println("│      ↓          ↘  ↓                │ e - City E       ├─────────────────────┐");
                System.out.println("│    a ●  ← ── ── →  ● b              │ f - Dirty Hills  │          N          │");
                System.out.println("│      ↑          ↗     ↖             │ g - City G       │     NW   │   NE     │");
                System.out.println("│      |       /     ↑     ╲          │                  │        ╲ │ /        │");
                System.out.println("│      |     /       |       ╲        │                  │  W ───── ● ───── E  │");
                System.out.println("│      ↓  ↙          |          ↘     │                  │        / │ ╲        │");
                System.out.println("│      ●  ← ── ── →  ●             ●  │                  │     SW   │   SE     │");
                System.out.println("│      c             d             e  │                  │          S          │");
                System.out.println("└─────────────────────────────────────┴──────────────────┴─────────────────────┘");
                break;
        }
//        System.out.println("┌────────────────────────┐");
//        System.out.println("│     HUB     Dirty Hills│");
//        System.out.println("│      ●  ← ─ ─ →  ●     │");
//        System.out.println("│      ↑  ↖        ↑     │");
//        System.out.println("│      |    ╲      |     │");
//        System.out.println("│      |      ╲    |     │");
//        System.out.println("│      ↓        ↘  ↓     │");
//        System.out.println("│      ●  ← ─ ─ →  ●     │");
//        System.out.println("│Solar City    Windy Town│");
//        System.out.println("└────────────────────────┘");

//        System.out.println("┌────────────────────────┐");
//        if (getRoomId()==0){
//            System.out.println("│     "
//                    + ANSI_RED +"HUB"+ ANSI_RESET +
//                    "     Dirty Hills│");
//            System.out.println("│      "
//                    + ANSI_RED +"●"+ ANSI_RESET +
//                    "  ← ─ ─ →  ●     │");
//        } else if (getRoomId()==1) {
//            System.out.println("│     HUB     "
//                    + ANSI_RED +"Dirty Hills"+ ANSI_RESET +
//                    "│");
//            System.out.println("│      ●  ← ─ ─ →  "
//                    + ANSI_RED +"●"+ ANSI_RESET +
//                    "     │");
//        } else {
//            System.out.println("│     HUB     Dirty Hills│");
//            System.out.println("│      ●  ← ─ ─ →  ●     │");
//        }
//        System.out.println("│      ↑  ↖        ↑     │");
//        System.out.println("│      |    ╲      |     │");
//        System.out.println("│      |      ╲    |     │");
//        System.out.println("│      ↓        ↘  ↓     │");
//        if (getRoomId()==3){
//            System.out.println("│      "
//                    + ANSI_RED +"●"+ ANSI_RESET +
//                    "  ← ─ ─ →  ●     │");
//            System.out.println("│"
//                    + ANSI_RED +"Solar City"+ ANSI_RESET +
//                    "    Windy Town│");
//        } else if (getRoomId()==2) {
//            System.out.println("│      ●  ← ─ ─ →  "
//                    + ANSI_RED +"●"+ ANSI_RESET +
//                    "     │");
//            System.out.println("│Solar City    "
//                    + ANSI_RED +"Windy Town"+ ANSI_RESET +
//                    "│");
//        } else {
//            System.out.println("│      ●  ← ─ ─ →  ●     │");
//            System.out.println("│Solar City    Windy Town│");
//        }
//        System.out.println("└────────────────────────┘");
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
    public Room getRoom() {return currentRoom;}

    public String get_curr_id() {
        return currentRoom.getRoomId().toString();
    }

}
