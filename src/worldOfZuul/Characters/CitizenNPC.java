package worldOfZuul.Characters;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CitizenNPC extends NPC {
    ArrayList<String> responses = new ArrayList<>();

    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";

    public CitizenNPC() {
        super();
    }
    public CitizenNPC(String name, Integer location, ArrayList<String> responses) {
        super(name, location);
        this.responses = responses;

    }
    @Override
    public void talk() {
        while (this.currentResponse.equals(this.lastResponse)) {
            this.currentResponse = responses.get((int)(Math.random()*responses.size()));
        }
        System.out.println("["+super.getName()+"] " + this.currentResponse);
        this.lastResponse = this.currentResponse;
    }
}
