package Misc;

import Characters.MainCharacter;
import worldOfZuul.Command;

import java.util.ArrayList;
import java.util.List;

public class PointShop {

    private ArrayList<Item> forSale_Hub = new ArrayList<>(List.of(
            new Item("SOLARPANEL", 200, 1, "Small Solar Panel"),
            new Item("HUGESOLARPANEL", 700, 2, "Huge Solar Panel"),
            new Item("WINDTURBINE", 100, 3, "Wooden Wind Turbine"),
            new Item("HUHEWINDTURBINE", 700, 4, "Industrial Wind Turbine"),
            new Item("CLEAN", 5000, 5, "Project Green Watts")
    ));

    private ArrayList<Item> forSale_Dirt = new ArrayList<>(List.of(
            new Item("FUELGEN", 75, 5, "Small Fuel Generator"),
            new Item("HUGEFUELGEN", 200, 6, "Big Fuel Generator")
    ));


    public PointShop() {
    }

    public ArrayList<Item> currentShop(int roomId){
        if (roomId == 0) {
            return forSale_Hub;
        } else if (roomId == 1) {
            return  forSale_Dirt;
        } else {
            return forSale_Hub;
        }
    }

    public boolean buyItem(Command command, MainCharacter mainCharacter, int roomId) {
        boolean noItemMatchingId = false;
        boolean notEnoughMoney = false;

        if (!command.hasCommandValue()) {
            //No item id on command.
            //Can't continue with BUY command.
            return false;
        }

        String selectedItem = command.getCommandValue();
        Item wantedItem = null;

        for (Item item : currentShop(roomId)) {
            if(item.getId().toString().equals(selectedItem)){
                wantedItem = item;
                if (item.getPrice() <= Money.getMoney()){
                    Money.removeMoney(item.getPrice());
                    mainCharacter.addToInventory(item);
                    return true;
                }
                notEnoughMoney = true;
            }
            noItemMatchingId = true;
        }

        if (notEnoughMoney) {
            return false;
        } else if (noItemMatchingId) {
            return false;
        }
        return false;
    }

    public ArrayList<Item> getForSale_Hub() {
        return forSale_Hub;
    }

    public ArrayList<Item> getForSale_Dirt() {
        return forSale_Dirt;
    }
}
