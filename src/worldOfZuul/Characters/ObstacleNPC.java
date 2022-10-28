package worldOfZuul.Characters;

import java.util.ArrayList;

public class ObstacleNPC extends NPC{

    ArrayList<String> responses = new ArrayList<>();

    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";

    public ObstacleNPC(String name, Integer location, ArrayList<String> responses) {
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

//    kunne kumunikere med komando "A 1" eller "A 2" for at vælge nogle forudindstillede svar
//
//    while (!overtalt)
//      not able to build
//
//    når overtalt
//      krav til hvor mange ting man skal have i INV for at kunne starte
//
//
//
//

}









