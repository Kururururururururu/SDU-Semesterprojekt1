package worldOfZuul.Characters;

import java.sql.SQLOutput;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ObstacleNPC extends NPC{

//    Attributes
    private static boolean convinced;
    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";
    ArrayList<Question> quiz;
    private Date lastTalk = new Date(0,0,0);
    private boolean didLeaveLast = false;
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
    }


//    Methods
    @Override
    public void talk() {
        if (isConvinced()){
            randomTalk();
        }
        else {
            conversation();
        }
    }

    public void randomTalk(){
        while (this.currentResponse.equals(this.lastResponse)) {
            this.currentResponse = responseAfterConvinced.get((int)(Math.random()*responseAfterConvinced.size()));
        }
        System.out.println("["+super.getName()+"] " + this.currentResponse);
        this.lastResponse = this.currentResponse;
    }

    public void conversation(){
        if ((new Date().getTime() - this.lastTalk.getTime()) >= 90000) {
            startQuiz();
            System.out.println("(conversation over)");
            this.lastTalk = new Date();
        }
        else if (((new Date().getTime() - this.lastTalk.getTime()) <= 20000) && !this.didLeaveLast){
            System.out.println("[" + super.getName() + "] " + "I SAID, come again another time!");
            System.out.println(((new Date().getTime() - this.lastTalk.getTime())/1000));
        }
        else {
            System.out.println("[" + super.getName() + "] " + "Come again later");
            System.out.println(((new Date().getTime() - this.lastTalk.getTime())/1000));
        }
    }
    public void startQuiz(){
        Integer score = 0;
        createQuestions();
        Scanner inputAnswer = new Scanner(System.in);
        boolean firstLine = true;

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println("--- To leave the conversation type " + ANSI_RED + "'leave'" + ANSI_RESET + " ---");

        outerloop:
        if (true){
            for (int i = 0; i < quiz.size(); i++) {
                System.out.println("["+super.getName()+"] " + quiz.get(i).getQuestion());
                System.out.println();
                if (firstLine){
                    System.out.println("(to answer write the number of the answer option you choose. (without a dot))");
                    firstLine = false;
                }
                System.out.println("--------- Answer options: ---------");
                for (int j = 0; j < quiz.get(i).getAnswerOptions().size(); j++) {
                    System.out.println(quiz.get(i).getAnswerOptions().get(j));
                }
                System.out.println();
                System.out.print("> ");
                String index = inputAnswer.next();
                if (index.equals("leave")){
                    System.out.println("["+super.getName()+"] Okay, we'll talk some other time");
                    this.didLeaveLast = true;
                    break outerloop;
                }
                else if (index.equals(quiz.get(i).getAnswer())){
                    score++;
                }

            }
            System.out.println("score = " + score + "/" + quiz.size());
            if (score == quiz.size()){
                this.convinced = true;
                System.out.println("(" + super.getName() + " is now convinced)");
            }
            else {
                System.out.println("["+super.getName()+"] Come again another time");
                this.didLeaveLast = false;
            }

        }
    }

    public void createQuestions(){
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









