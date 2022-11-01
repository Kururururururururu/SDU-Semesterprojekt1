package worldOfZuul.Characters;
import worldOfZuul.Command;
import worldOfZuul.Misc.*;
import worldOfZuul.Room;

public class MainCharacter {
    private String name;

    private Inventory player_inventory;

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
    public void showInventory() {
        player_inventory.showInventory();
    }

    public void useItem(Command command, Room currentRoom)    {
        if(currentRoom.getRoomId() == 0) {
            System.out.println("You cannot build in the hub!!");
            return;
        }

        if (!command.hasCommandValue()) {
            System.out.println("Please choose an item you want to use.");
            return;
        }

        Item forUse = new Item();
        /*
        for(Item item : player_inventory.getInventory())    {
            if(item.getType().toUpperCase() == command.getCommandValue()) {
                forUse = item;
                break;
            }
        }*/
        if(player_inventory.getInventory().size() > 0 && Integer.parseInt(command.getCommandValue())-1 <= player_inventory.getInventory().size())  {
            forUse = player_inventory.getInventory().get(Integer.parseInt(command.getCommandValue())-1);
        } else {
            System.out.println("Tried to use empty slot in inventory.");
            return;
        }
        if(forUse.getId() == 0) {
            System.out.println("No such item in invetory!");
            System.out.println(command.getCommandValue());
        } else {
            this.removeFromInventory(forUse);
            currentRoom.placeItem(forUse);
            System.out.println("Placed " + forUse.getType() + currentRoom.getShortDescription().substring(9));
        }


    }
}
