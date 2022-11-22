package worldOfZuul.Misc;

import worldOfZuul.Characters.MainCharacter;
import worldOfZuul.Command;

import java.util.ArrayList;
import java.util.List;

public class PointShop {

    ArrayList<Item> forSale_Hub = new ArrayList<>(List.of(
            new Item("Solar panel", 100, 1),
            new Item("Wind turbine", 100, 2)
    ));

    ArrayList<Item> forSale_Dirt = new ArrayList<>(List.of(
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

    public void openShop(int roomId) {

        System.out.println("Currently for sale: ");
        if (currentShop(roomId).size() != 0) {
            nameLengthDefiner(roomId);
            System.out.format("%" + nameLength + "s%4s%9s", "Product:", " ", "Price:");
            System.out.println();
            for (Item item : currentShop(roomId)) {
                System.out.format(item.getId() + ". %" + nameLength + "s%4s%9s", item.getType(), "|", "$" + item.getPrice().toString());
                System.out.println();
            }
        } else {
            System.out.println("No items for sale");
        }
        System.out.println();
        System.out.println("Use command 'Buy + product number' to purchase");
    }
    public void buyItem(Command command, MainCharacter mainCharacter, int roomId) {
        boolean noItemMatchingId = false;
        boolean notEnoughMoney = false;

        if (!command.hasCommandValue()) {
            //No item id on command.
            //Can't continue with BUY command.
            System.out.println("Want to buy what?");
            return;
        }

        String selectedItem = command.getCommandValue();
        Item wantedItem = null;

        for (Item item : currentShop(roomId)) {
            if(item.getId().toString().equals(selectedItem)){
                wantedItem = item;
                if (item.getPrice() <= Money.getMoney()){
                    Money.removeMoney(item.getPrice());
                    mainCharacter.addToInventory(item);
                    System.out.println("You bought a [" + item.getType() + "] for " + item.getPrice() + " points.");
                    System.out.println("Points left: " + Money.getMoney() + ".");
                    return;
                }
                notEnoughMoney = true;
            }
            noItemMatchingId = true;
        }

        if (notEnoughMoney) {
            System.out.println("You do not have enough $ to purchase a [" + wantedItem.getType() + "]. ("
                    + (wantedItem.getPrice()-Money.getMoney()) + " more needed)");
        } else if (noItemMatchingId) {
            System.out.println("There is no item matching that product number!");
        }
    }
    private Integer nameLengthDefiner(int roomId) {
        for (Item item : currentShop(roomId)) {
            if (item.getType().length() > nameLength) {
                nameLength = item.getType().length();
            }
        }
        return nameLength;
    }

}
