package Misc;

import java.util.*;

public class Inventory {
    private ArrayList<Item> player_inventory = new ArrayList<>();

    private Inventory() {

    }
    public void addToInventory(Item item) {
        //System.out.println(item.getType() + " added to inventory.");
        player_inventory.add(item);
    }

    public void removeFromInventory(Item item) {
        player_inventory.remove(item);
    }
    public ArrayList<Item> getInventoryUniques() {
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
    public ArrayList<Integer> getInventoryUniquesCount() {
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
            ArrayList<Integer> item_count = new ArrayList<>();
            for (Item item_uniques:player_inventory_uniques) {
                item_count.add(Collections.frequency(player_inventory, item_uniques));
            }
            return item_count;
        }
        return new ArrayList<Integer>();
    }

    public ArrayList<Item> getInventory() {
        return this.player_inventory;
    }
}
