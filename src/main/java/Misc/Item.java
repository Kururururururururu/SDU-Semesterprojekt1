package Misc;

import javafx.scene.effect.Light;

public class Item {
    private String type;
    private Integer id;
    private Integer price;

    private String name;
    public Item() {
        this.type = "UNKNOWN";
        this.price = 0;
        this.id = 0;
        this.name = "Generic Item";
    }
    public Item(String type, Integer price, Integer id, String name) {
        this.type = type;
        this.price = price;
        this.id = id;
        this.name = name;
    }

    public void generatePower(int roomID) {
        //Calculate the percentage of power generated in different areas.
        int exposure = 0;
        int value = 0;
        //System.out.println(this.type + roomID);
        if(this.type.toUpperCase().contains("SOLARPANEL"))   {
            if(roomID == 3) {
                exposure = 100;
                value = 75;
            } else {
                exposure = 33;
                value = 25;
            }
        } else {
            if(this.type.toUpperCase().contains("WINDTURBINE"))   {
                if(roomID == 2) {
                    exposure = 100;
                    value = 75;
                } else {
                    exposure = 33;
                    value = 25;
                }
            } else {
                if(this.type.toUpperCase().contains("FUELGEN"))   {
                    exposure = -10;
                    value = 100;
                }
            }
        }

        if(this.type.toUpperCase().contains("HUGE"))    {
            value = value*2;
            exposure = exposure*2;
        }

        //System.out.println(exposure);

        //Generate points based on exposure.
        Money.addMoney(value);
        Points.addPoints(exposure);
    }
    public String getType() {
        return this.type;
    }
    public Integer getPrice() {
        return this.price;
    }
    public Integer getId() { return this.id; }

    public String getName() { return this.name; }


}
