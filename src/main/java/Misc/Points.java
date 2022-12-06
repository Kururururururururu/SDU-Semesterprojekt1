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
        if (total_points < 999999-amount) {
            Points.total_points += amount;
            System.out.println("Points added: " + amount);
            System.out.println(total_points);
        }
        else {
            total_points = 999999;
            System.out.println("Max reached");

        }
    }
    public static Integer getPoints() {
        return Points.total_points;
    }
}
