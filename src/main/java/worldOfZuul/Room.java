package worldOfZuul;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import Misc.*;
import com.example.sdusemesterprojekt1.HelloController;

public class Room {
    private String description;
    private Integer RoomId;
    private HashMap<String, Room> exits;
    private static String lastExit = null;
    private long lastVisited = 0;
    private Inventory environmentInventory = new Inventory();

    public Room(String description, Integer RoomId) {
        this.RoomId = RoomId;
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public String getDescription() {
        return description;
    }


    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " |" + exit + "| ";
        }
        return returnString;
    }



    public void runEnvironment()  {
        //System.out.println(this.lastVisited);
        if(Instant.now().getEpochSecond() >= this.lastVisited+10)  {
            this.lastVisited = Instant.now().getEpochSecond();
            if(this.environmentInventory.getInventory().size() > 0) {
                for(Item item : this.environmentInventory.getInventory())   {
                    item.generatePower(this.RoomId);
                }
            }
            //System.out.println("Ran env");
            //this.environmentInventory.showInventory();
        }
    }

    public boolean placeItem(Item item) {
        if(this.hasRoomForItem(item)) {
            this.lastVisited = Instant.now().getEpochSecond();
            this.environmentInventory.addToInventory(item);
            runEnvironment();
            return true;
        } else {
            return false;
        }

    }

    public boolean hasRoomForItem(Item item) {

        return true;
    }


    public Room getExit(String direction) {
        return exits.get(direction);
    }
    
    public ArrayList<Item> getItems() {
        return this.environmentInventory.getInventory();
    }

    public String getLastExit() {return this.lastExit;}

    public Integer getRoomId() { return this.RoomId; }
    public void setLastExit(String lastExit){
        this.lastExit = lastExit;
    }
}

