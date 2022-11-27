package EventColliders;

import worldOfZuul.Game;


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
        game.goRoom(pathLeadsTo);
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

