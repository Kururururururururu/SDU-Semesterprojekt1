package Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainPlayer {
    private String name;

    private ArrayList<String> inv = new ArrayList<String>();

    public MainPlayer() {
        this.name = "Gary the coal king";
    }

    public boolean getInv() {
        System.out.println("INVENTORY: ");
        if (inv.size() != 0) {
            for (int i = 0; i < inv.size(); i++) {
                System.out.print("[" + inv.get(i) + "] ");
            }
        } else {
                System.out.print("Empty :(");
            }

        System.out.println();
        return true;
    }

    public void addToInv(String item) {
        inv.add(item);
        // System.out.println(item + " added!");
    }
}
