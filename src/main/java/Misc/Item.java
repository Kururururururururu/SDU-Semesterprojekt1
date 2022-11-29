package Misc;

public class Item {
    private String type;
    private String name;
    private Integer id;
    private Integer price;
    public Item() {
        this.type = "ITEM UNKNOWN";
        this.price = 0;
        this.id = 0;
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
        //System.out.println(this.type + roomID);
        if(this.type.toUpperCase().contains("SOLAR PANEL"))   {
            if(roomID == 3) {
                exposure = 100;
            } else {
                exposure = 33;
            }
        } else {
            if(this.type.toUpperCase().contains("WIND TURBINE"))   {
                if(roomID == 2) {
                    exposure = 100;
                } else {
                    exposure = 33;
                }
            }
        }

        //System.out.println(exposure);

        //Generate points based on exposure.
        Money.addMoney(4*exposure);
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
