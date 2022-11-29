package EventColliders;

import worldOfZuul.Game;

public class SolidCollider extends Collider {

    public SolidCollider() {
        super(0,0);
    }

    public SolidCollider(int x, int y) {
        super(x,y);
    }

    @Override
    public void onCollision(Game game) {
        // This is solid and has no event, leave empty.
        System.out.println("Solid collider hit");
    }

    @Override
    public boolean isColliding(int x, int y, String direction) {
        //Change the coordinates to prepare for the check.
        switch(direction) { // X & Y is inverted, and does not work if you correct it. I don't know why.
            case "up":
                x--;
                break;
            case "down":
                x++;
                break;
            case "left":
                y--;
                break;
            case "right":
                y++;
                break;
        }
        //Check if the coordinates are within the collider.
        if(x == this.x_pos && y == this.y_pos) {
            //System.out.println("Collision detected");
            return true;
        }
        return false;
    }
}

