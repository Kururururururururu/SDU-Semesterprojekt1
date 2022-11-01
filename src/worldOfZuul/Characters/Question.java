package worldOfZuul.Characters;

import java.util.ArrayList;

public class Question {
    String question;
    String answer;
    ArrayList<String> answerOptions;

    public Question(String question, ArrayList<String> answerOptions, String answer){
        this.question = question;
        this.answerOptions = answerOptions;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public ArrayList<String> getAnswerOptions() {
        return answerOptions;
    }
}
