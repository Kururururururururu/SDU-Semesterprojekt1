package Characters;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CitizenNPC extends NPC {
    ArrayList<String> responses = new ArrayList<>();
    private String true_name = "UNDEFINED";
    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";
    private Boolean first_talk = false;

    public CitizenNPC() {
        super();
    }
    public CitizenNPC(String name, String true_name, Integer location, ArrayList<String> responses) {
        super(name, location);
        this.true_name = true_name;
        this.responses = responses;
    }

    public String getTrue_name() {
        return true_name;
    }

    public Boolean getFirst_talk() {
        return first_talk;
    }

    public void setFirst_talk(Boolean first_talk) {
        this.first_talk = first_talk;
    }

    @Override
    public String talk() {
        while (this.currentResponse.equals(this.lastResponse)) {
            this.currentResponse = responses.get((int)(Math.random()*responses.size()));
        }
        this.lastResponse = this.currentResponse;
        return this.currentResponse;
    }
}
