package worldOfZuul.Misc;

public class Item {
    private String type;
    private Integer id;
    private Integer price;
    public Item() {
        this.type = "ITEM UNKNOWN";
        this.price = 0;
        this.id = 0;
    }
    public Item(String type, Integer price, Integer id) {
        this.type = type;
        this.price = price;
        this.id = id;
    }
    public String getType() {
        return this.type;
    }
    public Integer getPrice() {
        return this.price;
    }

}
