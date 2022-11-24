package EventColliders;

import com.example.sdusemesterprojekt1.HelloApplication;
import worldOfZuul.Game;

import java.util.ArrayList;

public class RoomchangeCollider extends Collider {

    private String pathLeadsTo;

    public RoomchangeCollider() {
        super(new ArrayList<>(), new ArrayList<>());
        this.pathLeadsTo = "hub";
    }

    public RoomchangeCollider(ArrayList<Integer> TstartPosition, ArrayList<Integer> TendPosition, String pathLeadsTo) {
        super(TstartPosition, TendPosition);
        this.pathLeadsTo = pathLeadsTo;
    }

    @Override
    public void onCollision(Game game) {
        // TODO Make the scene change as well.
        HelloApplication.SshowScene(pathLeadsTo);
        game.goRoom(pathLeadsTo);
    }

    @Override
    public boolean isColliding(int x, int y, String direction) {
        //Change the coordinates to prepare for the check.
        switch(direction) {
            case "up":
                y--;
                break;
            case "down":
                y++;
                break;
            case "left":
                x--;
                break;
            case "right":
                x++;
                break;
        }
        //Check if the coordinates are within the collider.
        if(x >= getStartPosition().get(0) && x <= getEndPosition().get(0)) {
            if(y >= getStartPosition().get(1) && y <= getEndPosition().get(1)) {
                return false;
            }
        }
        return true;
    }

}

