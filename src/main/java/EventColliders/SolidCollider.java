package EventColliders;

import worldOfZuul.Game;

import java.util.ArrayList;
import java.util.List;

public class SolidCollider extends Collider {

    public SolidCollider() {
        super(new ArrayList<>(), new ArrayList<>());
    }

    public SolidCollider(ArrayList<Integer> TstartPosition, ArrayList<Integer> TendPosition) {
        super(TstartPosition, TendPosition);
    }

    @Override
    public void onCollision(Game game) {
        // This is solid and has no event, leave empty.
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

