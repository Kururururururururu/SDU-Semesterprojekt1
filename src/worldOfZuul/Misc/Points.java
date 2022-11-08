package worldOfZuul.Misc;

public class Points {
    private static Integer total_points;
    public Points(Integer total_points) {
        Points.total_points = total_points;
    }

    public static void removePoints(Integer amount) {
        Points.total_points -= amount;
    }
    public static void addPoints(Integer amount) {
        Points.total_points += amount;
    }
    public Integer getPoints() {
        return Points.total_points;
    }
}
