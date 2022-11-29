package Characters;
import com.example.sdusemesterprojekt1.HelloController;
import worldOfZuul.Command;
import Misc.*;
import worldOfZuul.Room;

public class MainCharacter {
    private String name;

    private Inventory player_inventory;
    private HelloController controller;

    public MainCharacter() {

    }
    public MainCharacter(String name) {
        this.name = name;
        this.player_inventory = new Inventory();
    }
    public void addToInventory(Item item) {
        player_inventory.addToInventory(item);
    }

    public void removeFromInventory(Item item) {
        player_inventory.removeFromInventory(item);
    }

    public boolean useItem(Command command, Room currentRoom, HelloController controller) {
        if(currentRoom.getRoomId() == 0) {
            return false;
        }

        if (!command.hasCommandValue()) {
            return false;
        }

        Item forUse = new Item();


        for(Item item : player_inventory.getInventory())    {
            if(item.getId() == Integer.parseInt(command.getCommandValue())) {
                forUse = item;
                break;
            }
        }

        //Works if inventory is sorted correctly, save for future.
        /*
        if(player_inventory.getInventory().size() > 0 && Integer.parseInt(command.getCommandValue())-1 <= player_inventory.getInventory().size())  {
            forUse = player_inventory.getInventory().get(Integer.parseInt(command.getCommandValue())-1);
        } else {
            System.out.println("Tried to use empty slot in inventory.");
            return;
        }*/

        if(forUse.getId() == 0) {
            // Find some alternative //System.out.println("No such item in invetory!");
        } else {
            this.removeFromInventory(forUse);
            currentRoom.placeItem(forUse);
            return true;
            // Find some alternative //System.out.println("Placed " + forUse.getType() + currentRoom.getDescription().substring(9));
        }

        return false;
    }
}
