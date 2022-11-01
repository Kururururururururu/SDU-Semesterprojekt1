package worldOfZuul;

import java.time.Instant;
import java.util.Set;
import java.util.HashMap;
import worldOfZuul.Misc.*;

public class Room {
    private String description;
    private Integer RoomId;
    private HashMap<String, Room> exits;

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

    public String getShortDescription() {
        return description;
    }

    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
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
        if(Instant.now().getEpochSecond() >= this.lastVisited+180*1000)  {
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

    public void placeItem(Item item) {
        this.environmentInventory.addToInventory(item);
        //this.environmentInventory.showInventory();
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Integer getRoomId() { return this.RoomId; }
}

