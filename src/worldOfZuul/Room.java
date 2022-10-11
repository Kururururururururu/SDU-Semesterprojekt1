package worldOfZuul;

import java.util.Set;
import java.util.HashMap;


public class Room 
{
    private String id;
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description, String ID) {
        this.id = ID;
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " |" + exit + "| ";
        }
        return returnString;
    }

    public String getid() {
        return this.id;
    }
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

