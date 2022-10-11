package PointSystem;


import java.util.List;

public class pointShop {
    private List<Integer> prices;
    private List<String> wares;

    pointShop() {
        wares.add("Wish Solar Panel");
        wares.add("Wish Wind Turbine");
        wares.add("Premium Solar Panel");
        wares.add("Premium Wind Turbine");

        prices.add(10);
        prices.add(15);
        prices.add(100);
        prices.add(150);
    }

    public void addWare(String wareName, int warePrice) {
        wares.add(wareName);
        prices.add(warePrice);
    }

    public void purchase(/*String wareName*/ int wareID, Points pointSystem) {
        if(pointSystem.getPoints() > prices.get(wareID)) {
            prices.set(wareID, pointSystem.getPoints() - prices.get(wareID));
            // Add item to inventory here <<<
        } else {
            System.out.println("You don't have enough points to buy this product.");
        }
    }


}
