package worldOfZuul.Characters;
import worldOfZuul.Misc.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class GuideNPC extends NPC {
    ArrayList<String> responses = new ArrayList<>(List.of(
            "The weather is nice today. Very sunny!",
            "Work work work!",
            "I love green energy!",
            "You should be thankful for this job!",
            "Remember to check my shop, once in a while!",
            "Good energy gives good points!",
            "What are you waiting for?! Go make some green energy!"
    ));
    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";

    public GuideNPC() {
        super();
    }
    public GuideNPC(String name, Integer location) {
        super(name, location);

    }
    @Override
    public void talk() {
        while (this.currentResponse.equals(this.lastResponse)) {
            this.currentResponse = responses.get((int)(Math.random()*responses.size()));
        }
        System.out.println("[Boss] " + this.currentResponse);
        this.lastResponse = this.currentResponse;
    }
}
