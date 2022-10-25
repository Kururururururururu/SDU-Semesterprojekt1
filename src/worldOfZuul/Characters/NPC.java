package worldOfZuul.Characters;
//This is the NPC super class
public abstract class NPC {
    private String name;
    private Integer location;


    public NPC() {
        this.name = "UNDEFINED";
        this.location = null;
    }

    public NPC(String name, Integer location) {
        this.name = name;
        this.location = location;
    }
    public NPC(String name) {
        this.name = name;
        this.location = null;
    }
    public NPC(Integer location) {
        this.name = "UNDEFINED";
        this.location = location;
    }
    public abstract void talk();

    public void setLocation(Integer location) {
        this.location = location;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocation() {
        return location;
    }




}


