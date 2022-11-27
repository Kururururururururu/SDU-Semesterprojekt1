package EventColliders;

import com.example.sdusemesterprojekt1.HelloApplication;
import worldOfZuul.Game;

import java.util.ArrayList;

public class RoomchangeCollider extends Collider {

    private String pathLeadsTo;

    public RoomchangeCollider() {
        super(0,0);
        this.pathLeadsTo = "hub";
    }

    public RoomchangeCollider(int x_pos, int y_pos, String pathLeadsTo) {
        super(x_pos, y_pos);
        this.pathLeadsTo = pathLeadsTo;
    }

    @Override
    public void onCollision(Game game) {
        game.showScene(pathLeadsTo);
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
        if(x == this.x_pos && y == this.y_pos) {
            return false;
        }
        return true;
    }

}

