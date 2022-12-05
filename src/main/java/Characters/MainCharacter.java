package Characters;
import worldOfZuul.Command;
import Misc.*;
import worldOfZuul.Game;
import worldOfZuul.Room;

import java.io.IOException;

public class MainCharacter {
    private String name;

    private Inventory player_inventory;
    private Game game;

    public MainCharacter() {

    }
    public MainCharacter(String name, Game game) {
        this.name = name;
        this.player_inventory = new Inventory();
        this.game = game;
    }
    public Inventory getPlayer_inventory() {return player_inventory;}
    public void addToInventory(Item item) {
        if(item.getType() == "CLEAN")   {
            game.getController().isCleanDirtyHills(true);
        } else {
            player_inventory.addToInventory(item);
        }
    }

    public void removeFromInventory(Item item) {

        player_inventory.removeFromInventory(item);
        game.getController().onBagCloseButtonClick();
        game.getController().onBagButtonClick();
    }

    public boolean useItem(int index, Room currentRoom) {
        if (index < 0 || index >= 8) {
            return false;
        }

        if(player_inventory.getInventory().size() == 0) {
            return false;
        }

        if(this.getPlayer_inventory().getInventory().get(index) != null)    {
            Item forUse = this.getPlayer_inventory().getInventory().get(index);
                if(currentRoom.hasRoomForItem(forUse))  {
                    this.removeFromInventory(forUse);
                    currentRoom.placeItem(forUse);
                    game.getController().renderRoomItems(game.getRoom());
                    return true;
                }

        }
        return false;
    }
}
