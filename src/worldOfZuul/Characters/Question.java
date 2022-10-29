package worldOfZuul.Characters;

import java.util.ArrayList;

public class Question {
    String question;
    Integer answer;
    ArrayList<String> answerOptions;

    public Question(String question, ArrayList<String> answerOptions, Integer answer){
        this.question = question;
        this.answerOptions = answerOptions;
        this.answer = answer;
    }

}
