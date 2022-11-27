package worldOfZuul;

import java.time.Instant;
import java.util.Set;
import java.util.HashMap;
import Misc.*;

public class Room {
    private String description;
    private Integer RoomId;
    private HashMap<String, Room> exits;

    private long lastVisited = 0;
    private Inventory environmentInventory = new Inventory();

    private int solarSlots = 0, windSlots = 0;

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
        if(item.getType().equals("solar") && this.solarSlots > 0) {
            this.environmentInventory.addToInventory(item);
            this.solarSlots--;
            //TODO Put a render method here.

            return true;
        } else if(item.getType().equals("wind") && this.windSlots > 0 || item.getType().equals("solar") && this.windSlots > 0) {
            this.environmentInventory.addToInventory(item);
            this.windSlots--;
            //TODO Put a render method here.

            return true;
        }
        return false;
    }

    public int getSolarSlots() {
        return this.solarSlots;
    }

    public int getWindSlots() {
        return this.windSlots;
    }

    public void setSlots(int solarSlots, int windSlots) {
        this.solarSlots = solarSlots;
        this.windSlots = windSlots;
    }

    public boolean checkSlot(String type)   {
        if(type.equals("solar")) {
            for (Item item : this.environmentInventory.getInventory()) {
                if(item.getType().equals("solar")) {
                    this.solarSlots--;
                }
            }

            if (this.solarSlots > 0) {
                return true;
            }
        }
        else if(type.equals("wind")) {
            for(Item item : this.environmentInventory.getInventory()) {
                if(item.getType().equals("wind")) {
                    this.windSlots--;
                }
            }

            if(this.windSlots > 0)  {
                return true;
            }
        }
        return false;
    }


    public Room getExit(String direction) {
        return exits.get(direction);
    }
    


    public Integer getRoomId() { return this.RoomId; }
}

