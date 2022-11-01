package worldOfZuul.Characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

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
    private static boolean convinced;

    private String lastResponse = "UNDEFINED";
    private String currentResponse = "UNDEFINED";
    ArrayList<Question> quiz;
//    private Scanner inputAnswer;

    private ArrayList<String> responseAfterConvinced = new ArrayList<>(List.of(
            "Tak for at du overtalte mig.",
            "Det er en god ide at lave grøn energi.",
            "Svar 3",
            "Svar 4"
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
            startQuiz();
            System.out.println("(conversation over)");
        }
    }

    public void startQuiz(){
        Integer score = 0;
        createQuestions();
        Scanner inputAnswer = new Scanner(System.in);

        String ANSI_RESET = "\u001B[0m";
        String ANSI_RED = "\u001B[31m";
        System.out.println("--- To leave the conversation type " + ANSI_RED + "'leave'" + ANSI_RESET + " ---");

        outerloop:
        if (true){
            for (int i = 0; i < quiz.size(); i++) {
                System.out.println("["+super.getName()+"] " + quiz.get(i).getQuestion());
                System.out.println();
                System.out.println("--------- Answer options: ---------");
                for (int j = 0; j < quiz.get(i).getAnswerOptions().size(); j++) {
                    System.out.println(quiz.get(i).getAnswerOptions().get(j));
                }
                System.out.println();
                System.out.print("> ");
                String index = inputAnswer.next();
                if (index.equals("leave")){
                    System.out.println("["+super.getName()+"] Okay, we'll talk some other time");
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
            }

        }
    }

    public void createQuestions(){
        quiz = new ArrayList<Question>(List.of(
                new Question("Hvad er svaret? 1",
                        new ArrayList<String>(List.of("1. (svar)", "2. (svar)")),
                        "2"),
                new Question("Hvad er svaret? 2",
                        new ArrayList<String>(List.of("1. (svar)", "2. (svar)")),
                        "1"),
                new Question("Hvad er svaret? 3",
                        new ArrayList<String>(List.of("1. (svar)", "2. (svar)")),
                        "1")
        ));
    }

//    public void createInputScanner(){
//        this.inputAnswer = new Scanner(System.in)
//    }


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









