package Characters;
import Misc.*;
import worldOfZuul.Game;
import worldOfZuul.Room;


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
        Item forUse = new Item();
        for(int i = 0; i < this.player_inventory.getInventory().size(); i++)  {
            if(foundItemInInventory(i, index)) {
                forUse = this.player_inventory.getInventory().get(i);
            }
        }

        if(forUse != null)    {
            if(currentRoom.hasRoomForItem(forUse))  {
                if(currentRoom.placeItem(forUse))   {
                    this.removeFromInventory(forUse);
                    game.getController().renderRoomItems(game.getRoom());
                    return true;
                } else {
                    return false;
                }

            }

        }
        return false;
    }


    public void addToInventory(Item item) {
        if(item.getType() == "CLEAN")   {
            game.getController().isCleanDirtyHills(true);
        } else {
            player_inventory.addToInventory(item);
        }
    }

    private boolean foundItemInInventory(int i, int index) {
        if (this.player_inventory.getInventory().get(i).getId() == this.getPlayer_inventory().getInventoryUniques().get(index).getId()) {
            return true;
        } else {
            return false;
        }

    }

    public Inventory getPlayer_inventory() {return player_inventory;}


}
