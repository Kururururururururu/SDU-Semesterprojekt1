package worldOfZuul.Misc;

import java.util.*;

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
        ArrayList<Item> player_inventory_uniques = new ArrayList<>();
        Set<Item> unique_items = new HashSet<>(player_inventory);
        player_inventory_uniques.addAll(unique_items);


        System.out.print("Current inventory: ");
        if (player_inventory.size() != 0) {
            int list_length_count = 1;

            for (Item item_uniques : player_inventory_uniques) {
                int item_count = Collections.frequency(player_inventory, item_uniques);

                if (item_count > 1) {
                    System.out.print(item_count + "x");
                }
                if (list_length_count == player_inventory_uniques.size()){
                    System.out.print("[" + item_uniques.getType() + "]");
                } else {
                    System.out.print("[" + item_uniques.getType() + "], ");
                }
                list_length_count++;
            }
        } else {
            System.out.println("empty");
        }
        System.out.println();
    }

    public ArrayList<Item> getInventory() {
        return this.player_inventory;
    }
}
