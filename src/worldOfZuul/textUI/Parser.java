package worldOfZuul.textUI;

import worldOfZuul.Command;
import worldOfZuul.Game;

import java.util.Scanner;

public class Parser 
{
    private Scanner reader;
    private final Game game;

    public Parser(Game game) {
        this.game = game;
        this.reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> "); 

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            word1 = word1.toLowerCase();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
                word2 = word2.toUpperCase();
            }
        }
        return game.getCommand(word1,word2);
    }
}
