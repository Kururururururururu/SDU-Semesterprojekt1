package worldOfZuul;

import java.time.Instant;
import java.util.Set;
import java.util.HashMap;
import Misc.*;
import com.example.sdusemesterprojekt1.HelloController;

public class Room {
    private String description;
    private Integer RoomId;
    private HashMap<String, Room> exits;

    private long lastVisited = 0;
    private Inventory environmentInventory = new Inventory();
    private HelloController controller;

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

    public void runEnvironment()  {
        //System.out.println(this.lastVisited);
        if(this.environmentInventory.getInventory().size() > 0) {
            renderInventory(controller);
        }
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

    private void renderInventory(HelloController controller) {
        for (Item item : this.environmentInventory.getInventory()) {
            controller.renderItem(item);
        }
    }

    public boolean placeItem(Item item) {
        if(item.getType().equals("solarpanel") && this.solarSlots > 0) {
            this.environmentInventory.addToInventory(item);
            this.solarSlots--;
            renderInventory(controller);

            return true;
        } else if(item.getType().equals("windturbine") && this.windSlots > 0 || item.getType().equals("solar") && this.windSlots > 0) {
            this.environmentInventory.addToInventory(item);
            this.windSlots--;
            renderInventory(controller);

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

    public void grabController(HelloController controller) {
        this.controller = controller;
    }
    


    public Integer getRoomId() { return this.RoomId; }
}

