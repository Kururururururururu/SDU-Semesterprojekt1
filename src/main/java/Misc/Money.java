package Misc;

import com.example.sdusemesterprojekt1.HelloApplication.*;

public class Money {
    private static Integer total_money = 0;


    public static void removeMoney(Integer amount) {
        Money.total_money -= amount;
    }

    public static void addMoney(Integer amount) {
        if (total_money < 9999-amount) {
            Money.total_money += amount;
            //System.out.println("Money added");
            //System.out.println(total_money);
        }
        else {
            total_money = 9999;
            //System.out.println("Max reached");

        }



    }

    public static Integer getMoney() {
        return Money.total_money;
    }
}