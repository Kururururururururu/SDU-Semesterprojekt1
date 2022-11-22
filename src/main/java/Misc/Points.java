package Misc;

public class Points {
    private static Integer total_points = 0;
    public Points(Integer total_points) {
        Points.total_points = total_points;
    }

    public static void removePoints(Integer amount) {
        Points.total_points -= amount;
    }
    public static void addPoints(Integer amount) {
        Points.total_points += amount;
    }
    public static Integer getPoints() {
        return Points.total_points;
    }
}
