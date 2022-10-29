package worldOfZuul.Characters;

import java.util.ArrayList;
import java.util.List;

public class ObstacleNPC extends NPC{
//
//    ArrayList<String> responses = new ArrayList<>();
//
//    private String lastResponse = "UNDEFINED";
//    private String currentResponse = "UNDEFINED";
//
//    public ObstacleNPC(String name, Integer location, ArrayList<String> responses) {
//        super(name, location);
//        this.responses = responses;
//    }
//
//    @Override
//    public void talk() {
//        while (this.currentResponse.equals(this.lastResponse)) {
//            this.currentResponse = responses.get((int)(Math.random()*responses.size()));
//        }
//        System.out.println("["+super.getName()+"] " + this.currentResponse);
//        this.lastResponse = this.currentResponse;
//    }
    private boolean convinced;

    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";

    private ArrayList<String> responseAfterConvinced = new ArrayList<>(List.of(
            "Tak for at du overtalte mig.",
            "Det er en god ide at lave grøn energi."
            ));



    public ObstacleNPC(String name, Integer location, boolean convinced) {
        super(name, location);
        this.convinced = convinced;
    }

    @Override
    public void talk() {
        if (isConvinced()){
            while (this.currentResponse.equals(this.lastResponse)) {
                this.currentResponse = responseAfterConvinced.get((int)(Math.random()*responseAfterConvinced.size()));
            }
            System.out.println("["+super.getName()+"] " + this.currentResponse);
            this.lastResponse = this.currentResponse;

        }
        else {
            System.out.println("you have to quiz me");
        }
    }

    public boolean isConvinced() {
        return convinced;
    }
    public void setConvinced(boolean convinced) {
        this.convinced = convinced;
    }
//    kunne kommunikere med komando "A 1" eller "A 2" for at vælge nogle forudindstillede svar
//
//    while (!overtalt)
//      not able to build
//
//    når overtalt
//      krav til hvor mange ting man skal have i INV for at kunne starte



}









