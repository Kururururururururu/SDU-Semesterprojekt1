package EventColliders;

import java.util.ArrayList;

public abstract class Collider {
    private ArrayList<Integer> startPosition;
    private ArrayList<Integer> endPosition;

    public Collider(ArrayList<Integer> startPosition, ArrayList<Integer> endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public abstract void onCollision();

    public ArrayList<Integer> getStartPosition() {
        return startPosition;
    }

    public ArrayList<Integer> getEndPosition() {
        return endPosition;
    }
}
