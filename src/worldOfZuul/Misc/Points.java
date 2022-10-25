package worldOfZuul.Misc;

public class Points {
    private Integer total_points;
    public Points(Integer total_points) {
        this.total_points = total_points;
    }

    public void removePoints(Integer amount) {
        this.total_points -= amount;
    }
    public void addPoints(Integer amount) {
        this.total_points += amount;
    }
    public Integer getPoints() {
        return this.total_points;
    }
}
