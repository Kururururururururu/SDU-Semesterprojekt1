package worldOfZuul.Misc;

public class Money {
    private static Integer total_money = 0;

    public static void removeMoney(Integer amount) {
        Money.total_money -= amount;
    }

    public static void addMoney(Integer amount) {
        Money.total_money += amount;
    }

    public static Integer getMoney() {
        return Money.total_money;
    }
}
