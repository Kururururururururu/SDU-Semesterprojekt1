package worldOfZuul.Misc;

import java.util.ArrayList;

public class Inventory {
    ArrayList<Item> player_inventory = new ArrayList<>();

    public Inventory() {

    }
    public void addToInventory(Item item) {
        player_inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        player_inventory.remove(item);
    }
    public void showInventory() {
        System.out.print("Current inventory: | ");
        if (player_inventory.size() != 0) {
            for (Item item : player_inventory) {
                System.out.print(item.getType() + " | ");
            }
        } else {
            System.out.println("empty |");
        }
        System.out.println();
    }
}
