package EventColliders;

import worldOfZuul.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class Collider {
    protected int x_pos, y_pos;

    public Collider(int x, int y) {
        this.x_pos = x;
        this.y_pos = y;
    }

    public abstract void onCollision(Game game);
    public abstract boolean isColliding(int x, int y, String direction);

    public ArrayList<Integer> getPosition() {
        return new ArrayList<>(List.of(x_pos, y_pos));
    }

}
