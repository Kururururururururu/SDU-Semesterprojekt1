package worldOfZuul.Misc;

import worldOfZuul.Characters.MainCharacter;
import worldOfZuul.Command;
import worldOfZuul.Room;
import worldOfZuul.textUI.*;

import java.util.ArrayList;
import java.util.List;

public class PointShop {
    ArrayList<Item> forSale = new ArrayList<>(List.of(
            new Item("Solar panel", 100, 1),
            new Item("Wind turbine", 100, 2)
    ));
    Integer nameLength = 0;

    public PointShop() {

    }

    public void openShop() {
        System.out.println("Currently for sale: ");
        if (forSale.size() != 0) {
            nameLengthDefiner();
            System.out.format("%" + nameLength + "s%4s%9s", "Product:", " ", "Price:");
            System.out.println();
            for (Item item : forSale) {
                System.out.format(item.getId() + ". %" + nameLength + "s%4s%9s", item.getType(), "|", "$" + item.getPrice().toString());
                System.out.println();
            }
        } else {
            System.out.println("No items for sale");
        }
        System.out.println();
        System.out.println("Use command 'Buy + product number' to purchase");
    }
    public void buyItem(Command command, MainCharacter mainCharacter) {
        boolean noItemMatchingId = false;
        boolean notEnoughPoints = false;

        if (!command.hasCommandValue()) {
            //No item id on command.
            //Can't continue with BUY command.
            System.out.println("Want to buy what?");
            return;
        }

        String selectedItem = command.getCommandValue();
        Item wantedItem = null;

        for (Item item : forSale) {
            if(item.getId().toString().equals(selectedItem)){
                wantedItem = item;
                if (item.getPrice() <= Points.getPoints()){
                    Points.removePoints(item.getPrice());
                    mainCharacter.addToInventory(item);
                    System.out.println("You bought a [" + item.getType() + "] for " + item.getPrice() + " points.");
                    System.out.println("Points left: " + Points.getPoints() + ".");
                    return;
                }
                notEnoughPoints = true;
            }
            noItemMatchingId = true;
        }

        if (notEnoughPoints) {
            System.out.println("You do not have enough $ to purchase a [" + wantedItem.getType() + "]. (" + (wantedItem.getPrice()-Points.getPoints()) + " more needed)");
        } else if (noItemMatchingId) {
            System.out.println("There is no item matching that product number!");
        }
    }
    private Integer nameLengthDefiner() {
        for (Item item : forSale) {
            if (item.getType().length() > nameLength) {
                nameLength = item.getType().length();
            }
        }
        return nameLength;
    }

}
