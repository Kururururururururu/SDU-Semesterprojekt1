package Player;

public class NPC {
    private String name;
    private String role;

    public NPC() {
        name = "John Doe";
        role = "Villager";
    }

    public NPC(String NPC_name, String NPC_role) {
        name = NPC_name;
        role = NPC_role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
    public String talk() {
        return "Hello wanderer";
    }
}


