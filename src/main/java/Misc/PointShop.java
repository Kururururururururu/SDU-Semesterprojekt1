package Misc;

import Characters.MainCharacter;
import worldOfZuul.Command;
import worldOfZuul.Room;

import java.util.ArrayList;
import java.util.List;

public class PointShop {

    //TODO Remember this
    //private Inventory shopInventory;

    private ArrayList<Item> forSale_Hub = new ArrayList<>(List.of(
            new Item("solarpanel", 100, 1, "Small Solar Panel"),
            new Item("windturbine", 100, 2, "Small Wind Turbine")
    ));

    private ArrayList<Item> forSale_Dirt = new ArrayList<>(List.of(
            new Item("coalgenerator", 100, 1, "Small Coal Generator"),
            new Item("oilgenerator", 100, 2, "Small Oil Generator")
    ));

    ArrayList<Item> forSale;
    MainCharacter player;

    public PointShop() {
        forSale = new ArrayList<Item>();
    }

    public PointShop(Room room, MainCharacter player)  {
        if(room.getRoomId().equals(0)) {
            forSale = forSale_Hub;
        } else if(room.getRoomId().equals(1)) {
            forSale = forSale_Dirt;
        }
        this.player = player;
    }

    //TODO For possible future implementation
    /*
    private void stockShop()    {
        for(Item item : forSale)    {
            shopInventory.addToInventory(item);
        }
    }
     */

    public ArrayList<Item> getForSale() {
        return forSale;
    }

    public boolean buyItem(Item item) {
        if(Money.getMoney() >= item.getPrice()) {
            Money.removeMoney(item.getPrice());
            player.addToInventory(item);
            return true;
        } else {
            return false;
        }

    }

    public void displayShop()   {
        //TODO add proper function when inventory code is out.
    }


}
