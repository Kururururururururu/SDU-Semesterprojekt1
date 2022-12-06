package Characters;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ObstacleNPC extends NPC{

//    Attributes
    private static boolean convinced;
    private String lastResponse = "UNDEFINED";
    private int talkTime = 0;
    private String currentResponse = "UNDEFINED";
    ArrayList<Question> quiz;
    private Date lastTalk = new Date(0,0,0);
    private boolean didLeaveLast = false;
    private int score = 0;
    private ArrayList<String> responseAfterConvinced = new ArrayList<>(List.of(
            "Tak for at du overtalte mig.",
            "Det er en god ide at lave grøn energi.",
            "Svar 3",
            "Svar 4"
            ));


//    Constructors
    public ObstacleNPC(String name, Integer location, boolean convinced) {
        super(name, location);
        this.convinced = convinced;
        createQuestions();
    }


//    Methods
    @Override
    public String talk() {
        if (isConvinced()){
            randomTalk();
        }
        else {
            conversation();
        }
        return null;
    }

    public String randomTalk(){
        while (this.currentResponse.equals(this.lastResponse)) {
            this.currentResponse = responseAfterConvinced.get((int)(Math.random()*responseAfterConvinced.size()));
        }
        this.lastResponse = this.currentResponse;
        return this.currentResponse;
    }

    public ArrayList<String> conversation(){
        if ((new Date().getTime() - this.lastTalk.getTime()) >= 90000) {
            this.lastTalk = new Date();
            return new ArrayList<String>(List.of(quiz.get(talkTime).getQuestion(),
                                        quiz.get(talkTime).getAnswerOptions().toString(),
                                        quiz.get(talkTime).getAnswer()));
        }
        else if (((new Date().getTime() - this.lastTalk.getTime()) <= 20000) && !this.didLeaveLast){
            return new ArrayList<String>(List.of("I SAID, come again another time!"));
        }
        else {
            return new ArrayList<String>(List.of("Come again later"));
        }
    }

    public void addScore(int score){
        this.score += score;
    }

    private void createQuestions(){
        quiz = new ArrayList<Question>(List.of(
                new Question("Hello  (answer 1)",
                        new ArrayList<String>(List.of("1. Hello")),
                        "1"),
                new Question("What are you doing here it's private area?   (answer 1)",
                        new ArrayList<String>(List.of("1. I'm here to help the city set up some green and renewable energy sources. ")),
                        "1"),
                new Question("stop it. it's bad for my business!  (answer 1)",
                        new ArrayList<String>(List.of("1. But your coal power plant is bad for the climate, and besides, there are parts\n   of the city that don't get electricity at all.",
                                                      "2. Okay, but then your power plant has to supply power to the whole city")),
                        "1"),
                new Question("question? 4  (answer 1)",
                        new ArrayList<String>(List.of("1. (svar 1)",
                                                      "2. (svar 2)")),
                        "1"),
                new Question("question? 5  (answer 1)",
                        new ArrayList<String>(List.of("1. (svar 1)",
                                                      "2. (svar 2)")),
                        "1"),
                new Question("question? 6  (answer 1)",
                        new ArrayList<String>(List.of("1. (svar 1)",
                                                      "2. (svar 2)")),
                        "1"),
                new Question("question? 7  (answer 1)",
                        new ArrayList<String>(List.of("1. (svar 1)",
                                                      "2. (svar 2)")),
                        "1")
        ));
    }


//    Getters and Setters
    public boolean isConvinced() {
        return convinced;
    }

    public Date getLastTalk() {
        return lastTalk;
    }
}









