package PointSystem;

public class Points {
    private int points;

    public void addPoints(int amount) {
            this.points += amount;
    }

    public void removePoints(int amount) {
        this.points -= amount;
    }

    public int getPoints() {
        return points;
    }
}
