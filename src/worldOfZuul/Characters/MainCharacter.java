package worldOfZuul.Characters;
import worldOfZuul.Misc.*;

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
}
