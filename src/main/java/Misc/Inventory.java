package Misc;

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
    public List<Item> getInventoryUniques() {
        ArrayList<Item> player_inventory_uniques = new ArrayList<>();
        Set<Item> unique_items = new HashSet<>(player_inventory);
        player_inventory_uniques.addAll(unique_items);
        Collections.sort(player_inventory_uniques, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });

        if (player_inventory.size() != 0) {
            //int item_count = Collections.frequency(player_inventory, item_uniques);
            return player_inventory_uniques;
        }
        return new ArrayList<Item>();
    }

    public ArrayList<Item> getInventory() {
        return this.player_inventory;
    }
}
