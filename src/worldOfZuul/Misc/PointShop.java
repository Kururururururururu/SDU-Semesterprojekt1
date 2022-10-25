package worldOfZuul.Misc;

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
                System.out.format("%" + nameLength + "s%4s%9s", item.getType(), "|", "$" + item.getPrice().toString());
                System.out.println();
            }
        } else {
            System.out.println("No items for sale");
        }
        System.out.println();
        System.out.println("Use command 'Buy + product number' to purchase");
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
