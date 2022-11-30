package Misc;

import Characters.MainCharacter;
import worldOfZuul.Command;

import java.util.ArrayList;
import java.util.List;

public class PointShop {

    private ArrayList<Item> forSale_Hub = new ArrayList<>(List.of(
            new Item("Small Solar Panel", 100, 1),
            new Item("Huge Solar Panel", 1500, 2),
            new Item("Wooden Wind Turbine", 200, 3),
            new Item("Industrial Wind Turbine", 2500, 4)
    ));

    private ArrayList<Item> forSale_Dirt = new ArrayList<>(List.of(
            new Item("Coal generator", 100, 1),
            new Item("Oil generator", 100, 2)
    ));

//    ArrayList<Item> forSale;
    Integer nameLength = 0;

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
    private Integer nameLengthDefiner(int roomId) {
        for (Item item : currentShop(roomId)) {
            if (item.getType().length() > nameLength) {
                nameLength = item.getType().length();
            }
        }
        return nameLength;
    }

    public ArrayList<Item> getForSale_Hub() {
        return forSale_Hub;
    }

    public ArrayList<Item> getForSale_Dirt() {
        return forSale_Dirt;
    }
}
