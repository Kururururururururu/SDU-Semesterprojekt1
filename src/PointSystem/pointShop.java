package PointSystem;

import java.util.HashMap;
import java.util.List;

public class pointShop {
    private List<Integer> prices;
    private List<String> wares;

    pointShop() {
        wares.add("Solar Panel");
        wares.add("Wind Turbine");

        prices.add(10);
        prices.add(15);
    }

    public void addWare(String wareName, int warePrice) {
        wares.add(wareName);
        prices.add(warePrice);
    }


}
